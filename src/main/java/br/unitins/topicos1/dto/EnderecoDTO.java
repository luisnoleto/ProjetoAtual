package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
    @NotBlank(message = "O campo logradouro não pode estar vazio")
    String logradouro,

    @NotBlank(message = "O campo bairro não pode estar vazio")
    String bairro,

    @NotBlank(message = "O campo número não pode estar vazio")
    String numero,

    String complemento,

    @NotBlank(message = "O campo cep não pode estar vazio")
    String cep,

    @NotNull
    @Min(1)
    Long idMunicipio,

    // Incluindo os campos de cidade e estado
    @NotBlank(message = "O campo cidade não pode estar vazio")
    String cidade,

    @NotBlank(message = "O campo estado não pode estar vazio")
    String estado
) {

  
}
