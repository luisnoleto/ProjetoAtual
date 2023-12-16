package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.CartaoCredito;
import br.unitins.topicos1.model.Pedido;

public record CartaoCreditoResponseDTO( 

    Pedido idPedido,
    Double valor,
    String numeroCartao,
    String nomeImpressoCartao,
    String cpfTitular,
    LocalDate dataValidade,
    String codigoSeguranca
){    public static CartaoCreditoResponseDTO valueOf(CartaoCredito cartao){
        return new CartaoCreditoResponseDTO(
            cartao.getPedido(),
            cartao.getValor(),
            cartao.getNumeroDoCartao(),
            cartao.getNomeImpressoCartao(),
            cartao.getCpfDoTitular(),
            cartao.getDataValidade(),
            cartao.getCodigoSeguranca()
        );
    }
}
