package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.service.ClienteService;
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

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/clientes")
public class ClienteResource {
    
    @Inject
    public ClienteService service;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @POST
    public Response create(ClienteDTO dto){
        LOG.info("Executando create");
        LOG.debugf("DTO: %s", dto);
        return Response.ok(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete( @PathParam("id") Long id){
        LOG.info("Executando delete");
        LOG.infof("ID informado: "+id);
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, ClienteDTO dto){
        LOG.info("Executando update");
        LOG.debugf("New DTO: %s", dto);
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Cliente")
    @Path("/update-password/{idUsuario}")
    public Response updateUsuarioPassword( @PathParam("idUsuario") Long idUsuario, PasswordUpdateDTO passwordUpdateDTO){
        LOG.info("Executando updateUsuarioPassword");
        LOG.infof("ID informado: "+idUsuario);
        LOG.debugf("New DTO: %s", passwordUpdateDTO);
        service.updateUsuarioPassword(idUsuario, passwordUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Cliente")
    @Path("/update-username/{idUsuario}")
    public Response updateUsuarioUsername( @PathParam("idUsuario") Long idUsuario, UsernameUpdateDTO usernameUpdateDTO){
        LOG.info("Executando updateUsuarioPassword");
        LOG.infof("ID informado: "+idUsuario);
        LOG.debugf("New DTO: %s", usernameUpdateDTO);
        service.updateUsuarioUsername(idUsuario, usernameUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll(){
        LOG.info("Executando findAll");
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById( @PathParam("id") Long id){
        LOG.infof("Executando o metodo getById. Id: %s", id.toString());
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome( @PathParam("nome") String nome){
        LOG.infof("Executando o metodo findByNome. nome: %s", nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf( @PathParam("cpf") String cpf){
        LOG.infof("Executando o metodo findByCpf. cpf: %s", cpf);
        return Response.ok(service.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/username/{username}")
    public Response findByUsername( @PathParam("username") String username){
        LOG.infof("Executando o metodo findByUsername. username: %s", username);
        return Response.ok(service.findByUsername(username)).build();
    }
}
