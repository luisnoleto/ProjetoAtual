package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public interface UsuarioService {

    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) throws ConstraintViolationException;

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByLogin(String login);

    public List<UsuarioResponseDTO> findByAll(); 
    
}
