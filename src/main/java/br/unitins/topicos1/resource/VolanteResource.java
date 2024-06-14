package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.VolanteDTO;
import br.unitins.topicos1.dto.VolanteResponseDTO;
import br.unitins.topicos1.service.VolanteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/volantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VolanteResource {
    @Inject
    VolanteService volanteService;

    private static final Logger LOG = Logger.getLogger(VolanteResource.class);

    @GET
    public Response getAll(){
        LOG.info("Executando getAll");
        return Response.ok(volanteService.getAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id")Long id){
        LOG.infof("Executando o metodo getById. Id: %s", id.toString());
        return Response.ok(volanteService.getById(id)).build();
    }
    @GET
    @Path("/search/nome/{nome}")
    public Response getByNome(@PathParam("nome") String nome){
        LOG.infof("Executando o metodo getByNome. Nome: %s", nome);
        return Response.ok(volanteService.getByNome(nome)).build();
    }
    @GET
    @Path("/search/codigo/{codigo}")
    public Response getByCodigo(@PathParam("codigo") String codigo){
        LOG.infof("Executando o metodo getByCodigo. Id: %s", codigo.toString());
        VolanteResponseDTO response = volanteService.getByCodigo(codigo);
        if(response != null)
            return Response.ok(response).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    public Response create(VolanteDTO dto){
        LOG.info("Executando create");
        LOG.debugf("DTO: %s", dto);
        return Response
                .status(Response.Status.CREATED)
                .entity(volanteService.create(dto))
                .build();
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, VolanteDTO dto){
        LOG.info("Executando update");
        LOG.debugf("New DTO: %s", dto);
        volanteService.update(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long id){
        LOG.info("Executando delete");
        LOG.infof("ID informado: "+id);
        volanteService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
