package br.unitins.topicos1.dto;


import java.util.List;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO (
    @NotNull(message = "O campo Pagamento não pode ser nulo")
    Integer pagamento,
    @NotNull(message = "O campo endereco não pode ser nulo")
    Long endereco,
    @NotNull(message = "O campo Itens não pode ser nulo")
    List<ItemPedidoDTO> itens
) {

}
