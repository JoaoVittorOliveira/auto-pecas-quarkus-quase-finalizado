package br.unitins.topicos1.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.dto.AuthUsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService{
    
     private static final Duration EXPIRATION_TIME = Duration.ofHours(1);

    @Override
    public String generateJwt(AuthUsuarioDTO authDTO, UsuarioResponseDTO dto) {
        
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();
        if(authDTO.perfil() == 1){
            roles.add("Cliente");
        } else {
            // add outro perfil
        }

        return Jwt.issuer("fueltech-jwt")
            .subject(dto.username())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
}
