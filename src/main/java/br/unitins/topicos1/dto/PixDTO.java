package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record PixDTO(

        @NotBlank(message = "A chave pix deve ser informada.") 
        String chaveAleatoria,

        @NotBlank(message = "O tipo da chave deve ser informado.") 
        LocalDate dataExpiracaoTokenPix

) {

}
