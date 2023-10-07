package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GeneroResponseDTO;

public interface GeneroService {
    public GeneroResponseDTO insert(GeneroDTO dto);

    public GeneroResponseDTO update(GeneroDTO dto, Long id);

    public void delete(Long id);

    public GeneroResponseDTO findById(Long id);    

    public List<GeneroResponseDTO> findByName(String nome);    

    public List<GeneroResponseDTO> findByAll();


}
