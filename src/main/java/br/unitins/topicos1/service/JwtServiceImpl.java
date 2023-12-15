package br.unitins.topicos1.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(UsuarioResponseDTO usuario) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

    
       // Perfil perfil = usuario.perfil();

      //  String roleName = perfil.getLabel();

      Set<String> roleName = new HashSet<String>();
      roleName.add(usuario.perfil().getLabel());

        return Jwt.issuer("unitins-jwt")
            .subject(usuario.login())  
            .groups(roleName)
            .expiresAt(expiryDate)
            .sign();
    }
    
}