package br.unitins.topicos1.dto;



public record ItemPedidoDTO (
    Integer quantidade,
    Long idProduto, //idAlbum 
    Long tipoPagamento // 1-Boleto, 2-Cartao, 3-Pix
){
    
}
