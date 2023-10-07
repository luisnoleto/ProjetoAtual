package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;

import br.unitins.topicos1.dto.GravadoraDTO;
import br.unitins.topicos1.dto.GravadoraResponseDTO;
import br.unitins.topicos1.model.Gravadora;
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
public class GravadoraServiceImpl implements GravadoraService{

    @Inject
    GravadoraRepository repository;

    @Inject
    Validator validator;

    private void validar(GravadoraDTO gravadoraDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<GravadoraDTO>> violations = validator.validate(gravadoraDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public GravadoraResponseDTO insert(@Valid GravadoraDTO dto) throws ConstraintViolationException {
        validar(dto);
        Gravadora novoGravadora = new Gravadora();
        novoGravadora.setNome(dto.nome());

        repository.persist(novoGravadora);

        return GravadoraResponseDTO.valueOf(novoGravadora);
    }

    @Override
    @Transactional
    public GravadoraResponseDTO update(GravadoraDTO dto, Long id) {

        Gravadora gravadora = repository.findById(id);
        if (gravadora != null){
            gravadora.setNome(dto.nome());
        } else
            throw new NotFoundException();

        return GravadoraResponseDTO.valueOf(gravadora);
    }




    @Override
    @Transactional
    public void delete(Long id) {
        Gravadora gravadora = repository.findById(id);
        if (gravadora != null)
            repository.delete(gravadora);
        else
            throw new NotFoundException();
        }

    @Override
    public GravadoraResponseDTO findById(Long id) {
        return GravadoraResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<GravadoraResponseDTO> findByName(String nome) {
        return repository.findByName(nome).stream()
            .map(a -> GravadoraResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<GravadoraResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> GravadoraResponseDTO.valueOf(a)).toList();
    }
    
}
