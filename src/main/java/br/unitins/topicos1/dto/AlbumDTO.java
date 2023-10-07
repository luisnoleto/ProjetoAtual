package br.unitins.topicos1.dto;


import br.unitins.topicos1.model.TipoProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AlbumDTO (
    @NotBlank(message = "O nome deve ser informado")
    String nome,

    @Size(min = 4 , max = 4, message = "O ano de lançamento deve ter 4 caracteres")
    String anoLancamento,

    String descricao,

    Double preco,

    TipoProduto tipoProduto,

    Integer estoque,

    Long id_artista,

    Long id_genero,

    Long id_gravadora
    
)
{}