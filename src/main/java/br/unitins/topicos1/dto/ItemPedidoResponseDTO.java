package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO(
    Integer quantidade,
    Long idProduto,
    String nome
) { 
    public static ItemPedidoResponseDTO valueOf(ItemPedido item){
        return new ItemPedidoResponseDTO(
            item.getQuantidade(), 
            item.getAlbum().getId(),
            item.getAlbum().getNome());
    }

    public static List<ItemPedidoResponseDTO> valueOf(List<ItemPedido> item) {
       return item.stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList();
    }

}
