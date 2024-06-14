package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.service.AdministradorService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.ValidationException;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/Administradores")
public class AdministradorResource {
     @Inject
    public AdministradorService service;

    @POST
    public Response create(AdministradorDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete( @PathParam("id") Long id){
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, AdministradorDTO dto){
        // service.update(id, dto);
        // IMPLEMENTAR
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @PATCH
    @RolesAllowed("Administrador")
    @Path("/update-password/{idUsuario}")
    public Response updateUsuarioPassword( @PathParam("idUsuario") Long idUsuario, PasswordUpdateDTO passwordUpdateDTO){
        try {
            service.updateUsuarioPassword(idUsuario, passwordUpdateDTO);
        } catch (ValidationException e) {
            return Response.status(401).build();
        }
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Administrador")
    @Path("/update-username/{idUsuario}")
    public Response updateUsuarioUsername( @PathParam("idUsuario") Long idUsuario, UsernameUpdateDTO usernameUpdateDTO){
        service.updateUsuarioUsername(idUsuario, usernameUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome( @PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf( @PathParam("cpf") String cpf){
        return Response.ok(service.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/username/{username}")
    public Response findByUsername( @PathParam("username") String username){
        return Response.ok(service.findByUsername(username)).build();
    }
}
