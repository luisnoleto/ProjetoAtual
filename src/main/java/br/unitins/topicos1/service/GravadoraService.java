package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.GravadoraDTO;
import br.unitins.topicos1.dto.GravadoraResponseDTO;

public interface GravadoraService {
    public GravadoraResponseDTO insert(GravadoraDTO dto);

    public GravadoraResponseDTO update(GravadoraDTO dto, Long id);

    public void delete(Long id);

    public GravadoraResponseDTO findById(Long id);    

    public List<GravadoraResponseDTO> findByName(String nome);    

    public List<GravadoraResponseDTO> findByAll();


}
