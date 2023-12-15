package br.unitins.topicos1.dto;

import java.util.List;
import java.util.Map;

import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Pagamento;
import java.time.LocalDateTime;
import java.util.ArrayList;


public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataPedido,
        Pessoa usuario,
        Pagamento pagamento, 
        List<ItemPedidoResponseDTO> itens,
        Map<String, Object> endereco
)

{
    record Pessoa(Long id, String nome){}
   
    public static PedidoResponseDTO valueOf(Pedido pedido){
        List<ItemPedidoResponseDTO> itemPedidoResponseDTOList = new ArrayList<>();
        for (ItemPedido itemPedido : pedido.getItemPedido()) {
            itemPedidoResponseDTOList.add(ItemPedidoResponseDTO.valueOf(itemPedido));
        }

        Map<String, Object> endereco = EnderecoResponseDTO.valueOf(pedido.getEndereco()).toMap();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataPedido(),
                new Pessoa(pedido.getUsuario().getId(), pedido.getUsuario().getNome()),
                pedido.getPagamento(), 
                itemPedidoResponseDTOList,
                endereco);
    }

    
}
