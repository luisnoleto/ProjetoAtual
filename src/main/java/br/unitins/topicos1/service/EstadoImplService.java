package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;

@ApplicationScoped
public class EstadoImplService implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<EstadoResponseDTO> getAll() {

        return estadoRepository.findAll()
                                .stream()
                                .map(EstadoResponseDTO::new)
                                .collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO getById(Long id) throws NotFoundException {

        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Não encontrado");

        return new EstadoResponseDTO(estado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO insert(@Valid EstadoDTO estadoDto) throws ConstraintViolationException {

        validar(estadoDto);

        Estado entity = new Estado();

        entity.setNome(estadoDto.nome());

        entity.setSigla(estadoDto.sigla().toUpperCase());

        estadoRepository.persist(entity);

        return new EstadoResponseDTO(entity);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoDTO estadoDto) throws ConstraintViolationException {

        validar(estadoDto);

        Estado entity = estadoRepository.findById(id);

        entity.setNome(estadoDto.nome());

        entity.setSigla(estadoDto.sigla().toUpperCase());

        return new EstadoResponseDTO(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalArgumentException, NotFoundException {

        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Estado estado = estadoRepository.findById(id);

        if (estadoRepository.isPersistent(estado))
            estadoRepository.delete(estado);

        else
            throw new NotFoundException("Nenhum estado encontrado");
    }

    @Override
    public List<EstadoResponseDTO> getByNome(String nome) throws NullPointerException {

        List<Estado> list = estadoRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum Estado encontrado");

        return list.stream()
                    .map(EstadoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<EstadoResponseDTO> getBySigla(String sigla) throws NullPointerException {
        
        List<Estado> list = estadoRepository.findBySigla(sigla);

        if (list == null)
            throw new NullPointerException("nenhum Estado encontrado");

        return list.stream()
                    .map(EstadoResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public Long count() {

        return estadoRepository.count();
    }

    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
