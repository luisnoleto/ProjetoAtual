package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateSenhaDTO (
    @NotEmpty(message = "A senha atual não pode ser nula.")
    String senha,
    @NotEmpty(message = "a nova senha não pode ser nula.")
    String NovaSenha
) {

}