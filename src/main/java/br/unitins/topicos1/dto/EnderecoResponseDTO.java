package br.unitins.topicos1.dto;

import java.util.HashMap;
import java.util.Map;
import br.unitins.topicos1.model.Endereco;

public record EnderecoResponseDTO(
        String logradouro,
        String bairro,
        String numero,
        String complemento,
        String cep,
        Map<String, Object> municipio
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        Map<String, Object> municipio = new HashMap<>();
        municipio.put("nome", endereco.getMunicipio().getNome());
        municipio.put("estado", endereco.getMunicipio().getEstado());

        return new EnderecoResponseDTO(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                null
        );
    }
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("logradouro", logradouro);
        map.put("bairro", bairro);
        map.put("numero", numero);
        map.put("complemento", complemento);
        map.put("cep", cep);
        map.put("municipio", municipio);
        return map;
    }
}

