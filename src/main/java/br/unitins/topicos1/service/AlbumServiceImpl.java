package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;

import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;
import br.unitins.topicos1.model.Album;
import br.unitins.topicos1.repository.AlbumRepository;
import br.unitins.topicos1.repository.ArtistaRepository;
import br.unitins.topicos1.repository.GeneroRepository;
import br.unitins.topicos1.repository.GravadoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;



@ApplicationScoped
public class AlbumServiceImpl implements AlbumService{

    @Inject
    AlbumRepository repository;
    @Inject
    ArtistaRepository artistaRepository;
    @Inject
    GeneroRepository generoRepository;
    @Inject
    GravadoraRepository gravadoraRepository;


    @Inject
    Validator validator;

    private void validar(AlbumDTO albumDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<AlbumDTO>> violations = validator.validate(albumDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public AlbumResponseDTO insert(@Valid AlbumDTO dto) {
        validar(dto);
        Album novoAlbum = new Album();

        novoAlbum.setNome(dto.nome());
        novoAlbum.setAnoLancamento(dto.anoLancamento());
        novoAlbum.setArtista(artistaRepository.findById(dto.id_artista()));
        novoAlbum.setGenero(generoRepository.findById(dto.id_genero()));
        novoAlbum.setTipoProduto(dto.tipoProduto());
        novoAlbum.setGravadora(gravadoraRepository.findById(dto.id_gravadora()));
        novoAlbum.setDescricao(dto.descricao());
        novoAlbum.setPreco(dto.preco());
        novoAlbum.setEstoque(dto.estoque());

        repository.persist(novoAlbum);

        return AlbumResponseDTO.valueOf(novoAlbum);
    }

@Override
@Transactional
public AlbumResponseDTO update(AlbumDTO dto, Long id) {

    Album album = repository.findById(id);

        album.setNome(dto.nome());
        album.setAnoLancamento(dto.anoLancamento());
        album.setArtista(artistaRepository.findById(dto.id_artista()));
        album.setGravadora(gravadoraRepository.findById(dto.id_gravadora()));
        album.setGenero(generoRepository.findById(dto.id_genero()));
        album.setDescricao(dto.descricao());
        album.setPreco(dto.preco());
        album.setEstoque(dto.estoque());
        album.setTipoProduto(dto.tipoProduto());
    

    return AlbumResponseDTO.valueOf(album);
}

    @Override
    @Transactional
    public void delete(Long id) {
        Album  album = repository.findById(id);
        if (album != null)
            repository.delete(album);
        else
            throw new NotFoundException();
        }

    @Override
    public AlbumResponseDTO findById(Long id) {
        return AlbumResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<AlbumResponseDTO> findByName(String nome) {
        return repository.findByName(nome).stream()
            .map(a -> AlbumResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<AlbumResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> AlbumResponseDTO.valueOf(a)).toList();
    }
    
    @Override
    public void updateImage(String nomeImagem, Long id) {

        Album album = repository.findById(id);
        if(album==null)
            throw new NullPointerException("Album não encontrado");
        album.setNomeImagem(nomeImagem);
    }
}
