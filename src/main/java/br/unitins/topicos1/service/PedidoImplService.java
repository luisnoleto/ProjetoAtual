package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.BoletoBancario;
import br.unitins.topicos1.model.CartaoCredito;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Pix;
import br.unitins.topicos1.model.Album;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.BoletoBancarioRepository;
import br.unitins.topicos1.repository.CartaoCreditoRepository;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.ItemPedidoRepository;
import br.unitins.topicos1.repository.PixRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoImplService implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ItemPedidoRepository itemPedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PanacheRepository<? extends Album> albumRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    BoletoBancarioRepository boletoBancarioRepository;

    @Inject
    PixRepository pixRepository;

    @Inject
    CartaoCreditoRepository cartaoCreditoRepository;

    private static final Logger LOG = Logger.getLogger(PedidoImplService.class.getName());

    @Override
public List<PedidoResponseDTO> getAll(Long idUsuario) {
    Usuario usuario = usuarioRepository.findById(idUsuario);

    if (usuario == null) {
        throw new RuntimeException("Usuário não encontrado");
    }

    List<Pedido> list = pedidoRepository.findAll(idUsuario);

    if (list == null || list.isEmpty()) {
        throw new RuntimeException("Nenhum pedido encontrado para este usuário");
    }

    return list.stream().map(PedidoResponseDTO::valueOf).toList();
}


    @Override
    @Transactional
    public PedidoResponseDTO insert(Long idUsuario, ItemPedidoDTO itemPedidoDTO) throws NullPointerException {
        
        
        Album album = albumRepository.findById(itemPedidoDTO.idProduto());
        Usuario user = usuarioRepository.findById(idUsuario);
        //Endereco endereco = enderecoRepository.findById(user.getEndereco().getId());
        

        List<Pedido> pedidos = validar(idUsuario);

        //Integer indice = validar(album, pedido.getItemPedido()); //Verifica se o album já existe na lista de pedidos, se sim retorna a quantidade total de pedidos, se não cadastra o novo album

        ItemPedido itemPedido;

        /*if (indice != null) { 

            itemPedido = pedido.getItemPedido().get(indice);

            itemPedido.setQuantidade(itemPedidoDTO.quantidade());

        }*/


        //else {

            itemPedido = new ItemPedido(album, itemPedidoDTO.quantidade());
            //System.out.println("O id do pedido é: " + pedido.getId());
            int ultimoPedido = pedidos.size()-1;
            itemPedido.setPedido(pedidos.get(ultimoPedido));

            itemPedidoRepository.persist(itemPedido);

            
            pedidos.get(ultimoPedido).setItemPedido(itemPedido);
            pedidos.get(ultimoPedido).getItemPedido().add(itemPedido);
            pedidos.get(ultimoPedido).setEndereco(user.getEndereco());

        //}

        //Tratando da insercao do pedido
        Double valorTotal = itemPedido.getQuantidade() * itemPedido.getAlbum().getPreco();
        pedidos.get(ultimoPedido).setTotalPedido(valorTotal);
        pedidos.get(ultimoPedido).setDataPedido(LocalDateTime.now());

        //Adicionando ometodo de pagamento pix
        Pix pix = new Pix();
        pix.setDataExpiracaoTokenPix(LocalDate.now().plusDays(1));
        pix.setCpf(user.getCpf());
        pix.setNome(user.getNome());
        pix.setValor(valorTotal);

        pixRepository.persist(pix);
        pedidos.get(ultimoPedido).setPagamento(pix);

        pedidoRepository.persist(pedidos.get(ultimoPedido));



        ItemPedido ip = new ItemPedido();
        ip.setId(itemPedido.getId());
        ip.setAlbum(album);
        ip.setPedido(pedidos.get(ultimoPedido));
        ip.setQuantidade(itemPedidoDTO.quantidade());

        return PedidoResponseDTO.valueOf(pedidos.get(ultimoPedido));
    }

    @Override
    @Transactional
    public void delete(Long idUsuario, Long idAlbum) {

         Pedido pedido = pedidoRepository.findById(idUsuario);
        if (pedido != null)
            pedidoRepository.delete(pedido);
        else
            LOG.info("Pedido não encontrado");
        }


    @Override
    public void finalizarPedido(Long idPedido) throws NullPointerException {

        Pedido pedido = pedidoRepository.findById(idPedido);

        pedido.setDataPedido(LocalDateTime.now());

        for (ItemPedido itemPedido : pedido.getItemPedido()) {

            if (itemPedido.getAlbum().getEstoque() < itemPedido.getQuantidade())
                throw new NullPointerException("quantidade em estoque insuficiente para o requisitado requisitada. Não é possível finalizar a pedido");

            
        pedido.setEndereco(pedido.getUsuario().getEndereco());

        pedido.setIfConcluida(true);
         }
   }

    @Override
    @Transactional
    public void efetuarPagamentoBoleto(Long idUsuario) throws NullPointerException {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);
        
        Pedido pedido = validar(usuario);

        BoletoBancario pagamento = new BoletoBancario(pedido.getTotalPedido(), pedido.getUsuario().getNome(), pedido.getUsuario().getCpf());

        boletoBancarioRepository.persist(pagamento);

        pedido.setPagamento(pagamento);

        if (pedido.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finalizarPedido(pedido.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoPix(Long idUsuario) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);
        
        Pedido pedido = validar(usuario);

        Pix pagamento = new Pix(pedido.getTotalPedido(), pedido.getUsuario().getNome(), pedido.getUsuario().getCpf());

        pixRepository.persist(pagamento);

        pedido.setPagamento(pagamento);

        if (pedido.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finalizarPedido(pedido.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);

        Pedido pedido = validar(usuario);

        CartaoCredito pagamento = new CartaoCredito(pedido.getTotalPedido(),
                                            cartaoCreditoDTO.numeroCartao(),
                                            cartaoCreditoDTO.nomeImpressoCartao(),
                                            usuario.getCpf(),
                                            cartaoCreditoDTO.dataValidade(),
                                            cartaoCreditoDTO.codigoSeguranca());
                                           
        
        cartaoCreditoRepository.persist(pagamento);

        pedido.setPagamento(pagamento);

        if (pedido.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finalizarPedido(pedido.getId());
    }

    private Pedido validar(Usuario usuario) throws NullPointerException {

        Pedido pedido = pedidoRepository.findByUsuarioPedido(usuario);

        if (pedido == null)
            throw new NullPointerException("Não há nenhum pedido em andamento");

        return pedido;
    }

    private Integer validar(Album album, List<ItemPedido> listaItens) {
        for (int i = 0; i < listaItens.size(); i++) {
            if (listaItens.get(i).getAlbum().equals(album)) {
                return i;
            }
        }
        return null;
    }
    

    private List<Pedido> validar(Long idUsuario) {

        List<Pedido> pedidos = pedidoRepository.findByUsuarioPedidos(usuarioRepository.findById(idUsuario));
        
        Pedido newPedido = new Pedido(usuarioRepository.findById(idUsuario));

        pedidoRepository.persist(newPedido);

        pedidos.add(newPedido);

        return pedidos;
        
    }

    
}
