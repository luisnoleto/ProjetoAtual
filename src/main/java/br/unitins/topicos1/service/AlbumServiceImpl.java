package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;
import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.model.Album;
import br.unitins.topicos1.model.Genero;
import br.unitins.topicos1.repository.AlbumRepository;
import br.unitins.topicos1.repository.ArtistaRepository;
import br.unitins.topicos1.repository.GeneroRepository;
import br.unitins.topicos1.repository.GravadoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public AlbumResponseDTO insert(AlbumDTO dto) {
        Album novoAlbum = new Album();

        List<Genero> generos = new ArrayList<Genero>();
        novoAlbum.setNome(dto.nome());
        novoAlbum.setAnoLancamento(dto.anoLancamento());
        novoAlbum.setArtista(artistaRepository.findById(dto.id_artista()));

        for (GeneroDTO item : dto.id_genero()) {
           generos.add(generoRepository.findById(dto.id_genero()));
        }

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
        if (album != null){
            album.setNome(dto.nome());
            album.setAnoLancamento(dto.anoLancamento());
            album.setArtista(artistaRepository.findById(dto.id_artista()));

            List<Genero> generos = new ArrayList<Genero>();
            for (GeneroDTO item : dto.id_genero()) {
            generos.add(generoRepository.findById(dto.id_genero()));
        }
            album.setGravadora(gravadoraRepository.findById(dto.id_gravadora()));
            album.setDescricao(dto.descricao());
            album.setPreco(dto.preco());
            album.setEstoque(dto.estoque());
        } else
            throw new NotFoundException();

        return AlbumResponseDTO.valueOf(album);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
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
    
}
