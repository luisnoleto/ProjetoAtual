package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;

public interface AlbumService {
    public AlbumResponseDTO insert(AlbumDTO dto);

    public AlbumResponseDTO update(AlbumDTO dto, Long id);

    public void delete(Long id);

    public AlbumResponseDTO findById(Long id);    

    public List<AlbumResponseDTO> findByName(String nome);    

    public List<AlbumResponseDTO> findByAll();


}
