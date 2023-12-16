package br.unitins.topicos1.dto;

import java.time.LocalDate;

public record CartaoCreditoDTO(
    String numeroCartao,
    String nomeImpressoCartao,
    String cpfTitular,
    LocalDate dataValidade,
    String codigoSeguranca
) {
    
}
