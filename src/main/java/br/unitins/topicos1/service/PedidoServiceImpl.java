package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Album;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.AlbumRepository;
import br.unitins.topicos1.repository.EnderecoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    AlbumRepository AlbumRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    EnderecoRepository enderecoRepository;
    
    @Override
    @Transactional
        public PedidoResponseDTO insert(String login, @Valid PedidoDTO dto) {
            Pedido novoPedido = new Pedido();
            Usuario usuario = usuarioRepository.findByLogin(login);

            novoPedido.setUsuario(usuario);
            novoPedido.setDataPedido(LocalDateTime.now());
             Endereco endereco = enderecoRepository.findById(dto.endereco());
        if (endereco == null) {
            throw new NotFoundException("Endereco not found with id: " + dto.endereco());
        }
        novoPedido.setEndereco(endereco);


        novoPedido.setFormaPagamento(FormaPagamento.ValueOf(dto.pagamento()));

        novoPedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);

        Double total = 0.0;
        for (ItemPedidoDTO itemDto : dto.itens()) {
            Album album = AlbumRepository.findById(itemDto.idProduto());
            total += (album.getPreco() * itemDto.quantidade());
        }
        novoPedido.setTotalPedido(total);
        novoPedido.setItemPedido(new ArrayList<ItemPedido>());
        for (ItemPedidoDTO itemDto : dto.itens()) {
            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemDto.quantidade());

            Album Album = AlbumRepository.findById(itemDto.idProduto());
            item.setAlbum(Album);


            Album.setEstoque(Album.getEstoque() - item.getQuantidade());

            novoPedido.getItemPedido().add(item);
        }

  
        pedidoRepository.persist(novoPedido);

        if (dto.pagamento() == 1) {
            novoPedido.setVencimento(novoPedido.getDataPedido().plusMinutes(1));
        } else if (dto.pagamento() == 2) {
            novoPedido.setVencimento(novoPedido.getDataPedido().plusDays(2));
        }

        return PedidoResponseDTO.valueOf(novoPedido);

    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

   
    public List<PedidoResponseDTO> findByUsuario(Long idUsuario) {
        List<Pedido> pedidos = pedidoRepository.findAll(idUsuario);
        return pedidos.stream().map(PedidoResponseDTO::valueOf).collect(Collectors.toList());
    }

}
