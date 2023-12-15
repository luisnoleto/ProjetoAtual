package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public interface AlbumService {
    public AlbumResponseDTO insert(@Valid AlbumDTO dto) throws ConstraintViolationException;

    public AlbumResponseDTO update(AlbumDTO dto, Long id);

    public void delete(Long id);

    public AlbumResponseDTO findById(Long id);    

    public List<AlbumResponseDTO> findByName(String nome);    

    public List<AlbumResponseDTO> findByAll();

    public void updateImage(String nomeImagem, Long id);


}
