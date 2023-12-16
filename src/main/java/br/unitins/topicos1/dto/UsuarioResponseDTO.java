package br.unitins.topicos1.dto;

import java.util.List;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login,
    String nomeImagem,
    String cpf,
    Perfil perfil,
    List<EnderecoResponseDTO> listaEndereco,
    List<TelefoneDTO> listaTelefone
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        
         return new UsuarioResponseDTO(

            usuario.getId(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getNomeImagem(),
            usuario.getCpf(),
            usuario.getPerfil(),
            usuario.getEndereco().stream().map(e -> EnderecoResponseDTO.valueOf(e)).toList(),
            usuario.getListaTelefone().stream().map(t -> TelefoneResponseDTO.valueOf(t)).toList()
         );

     }
}
