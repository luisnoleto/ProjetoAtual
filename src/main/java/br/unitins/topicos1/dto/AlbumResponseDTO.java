package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Album;



public record AlbumResponseDTO(
    Long id,
    String nome,
    String anoLancamento,
    String descricao,
    Double preco,
    Integer estoque,
    ArtistaResponseDTO artista,
    List<GeneroResponseDTO> genero,
    GravadoraResponseDTO gravadora
) {
    public static AlbumResponseDTO valueOf(Album album){
        return new AlbumResponseDTO(
            album.getId(), 
            album.getNome(), 
            album.getAnoLancamento(),
            album.getDescricao(),
            album.getPreco(),
            album.getEstoque(),
            ArtistaResponseDTO.valueOf(album.getArtista()),
            album.getGenero().stream().map(g -> GeneroResponseDTO.valueOf(g)).toList(),
            GravadoraResponseDTO.valueOf(album.getGravadora())


            
        );
    }
 }
