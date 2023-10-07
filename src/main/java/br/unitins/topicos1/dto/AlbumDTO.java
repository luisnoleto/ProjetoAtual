package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AlbumDTO (
    @NotBlank(message = "O nome deve ser informado")
    String nome,

    @Size(min = 4 , max = 4, message = "O ano de lançamento deve ter 4 caracteres")
    String anoLancamento,

    String descricao,

    Double preco,

    Integer estoque,

    Long id_artista,

    List<GeneroDTO> id_genero,

    Long id_gravadora
    
)
{}