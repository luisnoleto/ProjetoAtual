package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;

public interface EstadoService {
    
    List<EstadoResponseDTO> getAll();
    
    EstadoResponseDTO getById(Long id);

    EstadoResponseDTO insert(EstadoDTO estadoDto);

    EstadoResponseDTO update(Long id, EstadoDTO estadoDto);

    void delete(Long id);

    // Metodos extras

    List<EstadoResponseDTO> getByNome(String nome);

    List<EstadoResponseDTO> getBySigla(String sigla);

    Long count();
}
