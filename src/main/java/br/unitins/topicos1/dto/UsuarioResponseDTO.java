package br.unitins.topicos1.dto;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

//import br.unitins.topicos1.dto.MunicipioResponseDTO;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.model.Municipio;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login,
    String nomeImagem,
    String cpf,
    Perfil perfil,
    Map<String, Object> endereco,
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
            viewEndereco(usuario.getEndereco().getLogradouro(),
                        usuario.getEndereco().getBairro(), 
                        usuario.getEndereco().getNumero(), 
                        usuario.getEndereco().getComplemento(), 
                        usuario.getEndereco().getCep(),
                        usuario.getEndereco().getMunicipio()),
            usuario.getListaTelefone().stream().map(t -> TelefoneResponseDTO.valeuOf(t)).toList()
         );

     }
         public static Map<String, Object> viewEndereco (String logradouro, String bairro, String numero, String complemento, String cep, Municipio municipio) {

        Map<String, Object> endereco = new HashMap<>();

        endereco.put("logradouro", logradouro);
        endereco.put("bairro", bairro);
        endereco.put("numero", numero);
        endereco.put("complemento", complemento);
        endereco.put("cep", cep);
        endereco.put("municipio", viewMunicipio(municipio.getNome(), municipio.getEstado()));

        return endereco;
    }

    public static Map<String, Object> viewMunicipio(String nome, Estado estado) {

        Map<String, Object> municipio = new HashMap<>();

        municipio.put("nome", nome);
        municipio.put("estado", MunicipioResponseDTO.viewEstado(estado.getNome(), estado.getSigla()));

        return municipio;
    }

}
