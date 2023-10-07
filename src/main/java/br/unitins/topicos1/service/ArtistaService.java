package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;

public interface ArtistaService {
    public ArtistaResponseDTO insert(ArtistaDTO dto);

    public ArtistaResponseDTO update(ArtistaDTO dto, Long id);

    public void delete(Long id);

    public ArtistaResponseDTO findById(Long id);    

    public List<ArtistaResponseDTO> findByName(String nome);    

    public List<ArtistaResponseDTO> findByAll();


}
