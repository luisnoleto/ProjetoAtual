package br.unitins.topicos1.dto;



import br.unitins.topicos1.model.Album;
import br.unitins.topicos1.model.TipoProduto;



public record AlbumResponseDTO(
    Long id,
    String nome,
    String anoLancamento,
    String descricao,
    Double preco,
    Integer estoque,
    ArtistaResponseDTO artista,
    GeneroResponseDTO genero,
    GravadoraResponseDTO gravadora,
    TipoProduto tipoProduto
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
            GeneroResponseDTO.valueOf(album.getGenero()),
            GravadoraResponseDTO.valueOf(album.getGravadora()),
            album.getTipoProduto()


            
        );
    }
 }
