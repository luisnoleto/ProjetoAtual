package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UsuarioDTO (
    @NotBlank(message = "O nome deve ser informado")
    String nome,
    @NotBlank(message = "O login deve ser informado")
    String login,
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    String senha,
    
    @NotNull(message = "O endereço deve ser informado")
    List<EnderecoDTO> endereco,
   
   @NotBlank(message = "O CPF deve ser informado")
    String cpf,
    
    @NotNull(message = "O Perfil deve ser informado")
    Perfil perfil,
    
    List<TelefoneDTO> listaTelefone

){

    
    
}
