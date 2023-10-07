package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsuarioDTO (
    @NotBlank(message = "O nome deve ser informado")
    String nome,
    @NotBlank(message = "O login deve ser informado")
    String login,
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    String senha,
    Boolean isAdmin,
    List<TelefoneDTO> listaTelefone

){

    
    
}
