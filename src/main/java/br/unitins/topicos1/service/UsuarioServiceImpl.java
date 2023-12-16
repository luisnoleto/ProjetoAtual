package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UpdateSenhaDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.MunicipioRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) throws ConstraintViolationException{
        validar(dto);
        if (usuarioRepository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(dto.perfil());

        if (dto.listaTelefone() != null &&
                !dto.listaTelefone().isEmpty()) {
            novoUsuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        } else
            throw new ValidationException("listaTelefone", "O usuário deve ter pelo menos um telefone.");
            

        return UsuarioResponseDTO.valueOf(novoUsuario);
        }
        
    

    @Override
    @Transactional
    public UsuarioResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setNomeImagem(nomeImagem);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(dto.perfil());
        
        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            usuario.getListaTelefone().clear();
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                usuario.getListaTelefone().add(telefone);
            }
        } else
            throw new ValidationException("listaTelefone", "O usuário deve ter pelo menos um telefone.");
        
        
        return UsuarioResponseDTO.valueOf(usuario);
        }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0)
            throw new ValidationException("id", "Id inválido!");
        Usuario usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(usuarioRepository.findById(id));
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        if (usuario == null) 
            throw new ValidationException("login", "Login ou senha incorretos!");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }


    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return usuarioRepository.listAll().stream()
        .map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public Usuario findByLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new ValidationException("login", "Login não existe!");
        }
        return usuario;
    }

    @Override
    @Transactional
      public void updateSenha(UpdateSenhaDTO dto, Long id) {
       
        Usuario usuario = usuarioRepository.findById(id);

      
        if (usuario.getSenha().equals(hashService.getHashSenha(dto.senha())))
            usuario.setSenha(hashService.getHashSenha(dto.NovaSenha())); 
        else
            throw new ValidationException("senha", "Senha incorreta!");
      }
       
}