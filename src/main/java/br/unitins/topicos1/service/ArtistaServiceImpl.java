package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import br.unitins.topicos1.model.Artista;
import br.unitins.topicos1.repository.ArtistaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class ArtistaServiceImpl implements ArtistaService{

    @Inject
    ArtistaRepository repository;

    @Inject
    Validator validator;

    private void validar(ArtistaDTO artistaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ArtistaDTO>> violations = validator.validate(artistaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public ArtistaResponseDTO insert(ArtistaDTO dto) {
        validar(dto);
        Artista novoArtista = new Artista();
        novoArtista.setNome(dto.nome());
        novoArtista.setDescricao(dto.descricao());

        repository.persist(novoArtista);

        return ArtistaResponseDTO.valueOf(novoArtista);
    }

    @Override
    @Transactional
    public ArtistaResponseDTO update(ArtistaDTO dto, Long id) {

        Artista artista = repository.findById(id);
        if (artista != null){
            artista.setNome(dto.nome());
            artista.setDescricao(dto.descricao());
        } else
            throw new NotFoundException();

        return ArtistaResponseDTO.valueOf(artista);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Artista artista = repository.findById(id);
        if (artista != null)
            repository.delete(artista);
        else
            throw new NotFoundException();
        }

    @Override
    public ArtistaResponseDTO findById(Long id) {
        return ArtistaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public List<ArtistaResponseDTO> findByName(String nome) {
        List<Artista> artistas = repository.findByName(nome);
        return artistas.stream()
            .map(a -> ArtistaResponseDTO.valueOf(a))
            .toList();
    }

    @Override
    public List<ArtistaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> ArtistaResponseDTO.valueOf(a)).collect(Collectors.toList());
    }
    
}
