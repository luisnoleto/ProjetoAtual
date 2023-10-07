package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GeneroResponseDTO;
import br.unitins.topicos1.model.Genero;
import br.unitins.topicos1.repository.GeneroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class GeneroServiceImpl implements GeneroService{

    @Inject
    GeneroRepository repository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public GeneroResponseDTO insert(GeneroDTO dto) {
        Genero novoGenero = new Genero();
        novoGenero.setNome(dto.nome());

        repository.persist(novoGenero);

        return GeneroResponseDTO.valueOf(novoGenero);
    }

    @Override
    @Transactional
    public GeneroResponseDTO update(GeneroDTO dto, Long id) {

        Genero genero = repository.findById(id);
        if (genero != null){
            genero.setNome(dto.nome());
        } else
            throw new NotFoundException();

        return GeneroResponseDTO.valueOf(genero);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
            throw new NotFoundException();
    }

    @Override
    public GeneroResponseDTO findById(Long id) {
        return GeneroResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<GeneroResponseDTO> findByName(String nome) {
        return repository.findByName(nome).stream()
            .map(a -> GeneroResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<GeneroResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> GeneroResponseDTO.valueOf(a)).toList();
    }
    
}
