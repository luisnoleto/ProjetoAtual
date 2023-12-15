package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.MunicipioDTO;
import br.unitins.topicos1.dto.MunicipioResponseDTO;

public interface MunicipioService {
    
    // Metodos basicos

    List<MunicipioResponseDTO> getAll();
    
    MunicipioResponseDTO getById(Long id);

    MunicipioResponseDTO insert(MunicipioDTO municipioDto);

    MunicipioResponseDTO update(Long id, MunicipioDTO municipioDto);

    void delete(Long id);

    List<MunicipioResponseDTO> getByNome(String nome);

    List<MunicipioResponseDTO> getByNomeEstado(String nomeEstado);

    List<MunicipioResponseDTO> getBySiglaEstado(String siglaEstado);
}
