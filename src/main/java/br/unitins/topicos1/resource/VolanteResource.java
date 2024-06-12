package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.VolanteDTO;
import br.unitins.topicos1.service.VolanteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/volantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VolanteResource {
    @Inject
    VolanteService volanteService;

    @GET
    public Response getAll(){
        return Response.ok(volanteService.getAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id")Long id){
        return Response.ok(volanteService.getById(id)).build();
    }
    @GET
    @Path("/search/nome/{nome}")
    public Response getByNome(@PathParam("nome") String nome){
        return Response.ok(volanteService.getByNome(nome)).build();
    }
    @GET
    @Path("/search/codigo/{codigo}")
    public Response getByCodigo(@PathParam("codigo") String codigo){
        return Response.ok(volanteService.getByCodigo(codigo)).build();
    }

    @POST
    public Response create(VolanteDTO dto){
        return Response
                .status(Response.Status.CREATED)
                .entity(volanteService.create(dto))
                .build();
    }
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, VolanteDTO dto){
        volanteService.update(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Long id){
        volanteService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}