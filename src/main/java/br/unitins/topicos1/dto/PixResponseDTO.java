package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Pix;



public record PixResponseDTO(
    Pedido idPedido,
    Double valor,
    String chaveAleatoria,
    LocalDate dataExpiracaoTokenPix

) {
        public static PixResponseDTO valueOf(Pix pix){
        return new PixResponseDTO(
            pix.getIdPedido(),
            pix.getValor(),
            pix.getChaveAleatoria(),
            pix.getDataExpiracaoTokenPix()
        );
    }
}
