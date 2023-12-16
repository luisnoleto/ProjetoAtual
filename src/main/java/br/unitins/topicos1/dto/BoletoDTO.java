package br.unitins.topicos1.dto;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoletoDTO(
        
        @NotBlank(message = "O número do boleto deve ser informado.")
        String numeroBoleto,

        @NotNull()
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime dataGeracaoBoleto,

        @NotNull()
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime dataVencimento


) {
    
}
