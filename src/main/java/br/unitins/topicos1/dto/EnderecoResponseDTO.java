package br.unitins.topicos1.dto;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Municipio;


public record EnderecoResponseDTO(
        String logradouro,
        String bairro,
        String numero,
        String complemento,
        String cep,
        Municipio idMunicipio
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getIdMunicipio()
        );
    }

}
