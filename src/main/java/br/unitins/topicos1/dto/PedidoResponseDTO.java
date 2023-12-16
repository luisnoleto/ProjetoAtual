package br.unitins.topicos1.dto;

import java.time.LocalDateTime;
import java.util.List;


import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime dataPedido,
    UsuarioResponseDTO usuario,
    EnderecoResponseDTO endereco,
    FormaPagamento pagamento,
    Double totalPedido,
    List<ItemPedidoResponseDTO> itens,
    StatusPedido statusPedio,
    LocalDateTime vencimento
) { 
    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(), 
            pedido.getDataPedido(),
            UsuarioResponseDTO.valueOf(pedido.getUsuario()),
            EnderecoResponseDTO.valueOf(pedido.getEndereco()),
            pedido.getFormaPagamento(),
            pedido.getTotalPedido(),
            ItemPedidoResponseDTO.valueOf(pedido.getItemPedido()),
            pedido.getStatusPedido(),
            pedido.getVencimento()
            );
    }
}
