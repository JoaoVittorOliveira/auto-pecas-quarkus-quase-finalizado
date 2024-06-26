package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.AuthUsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.AdministradorService;
import br.unitins.topicos1.service.ClienteService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthUsuarioResource {

    @Inject
    public ClienteService clienteService;

    @Inject
    public AdministradorService administradorService;

    @Inject
    public HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO dto){
        String hashSenha = hashService.getHashSenha(dto.senha());

        UsuarioResponseDTO usuario = null;

        if(dto.perfil() == 1){
            // cliente
            usuario = clienteService.login(dto.username(), hashSenha);
        } else if (dto.perfil() == 2){
            // administrador
            usuario = administradorService.login(dto.username(), hashSenha);
        } else {
            // perfil inexistente
            return Response.status(Status.NOT_FOUND).header("Perfil", "perfis existentes: 1-cliente").build();
        }

        if(usuario != null){
            return Response.ok(usuario).header("Authorization", jwtService.generateJwt(dto, usuario))
        .status(Status.CREATED)
        .build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
        
    }
}
