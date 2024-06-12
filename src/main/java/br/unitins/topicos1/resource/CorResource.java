package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.service.CorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CorResource {
    @Inject
    CorService corService;

    @GET
    public Response getAll(){
        return Response.ok(corService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        return Response.ok(corService.getById(id)).build();
    }
    @GET
    @Path("/search/nome/{nome}")
    public Response getByNome(@PathParam("nome") String nome){
        return Response.ok(corService.getByNome(nome)).build();
    }
    @GET
    @Path("/search/codigo/{codigo}")
    public Response getByCodigo(@PathParam("codigo") String codigo){
        return Response.ok(corService.getByCodigo(codigo)).build();
    }

    @POST
    public Response create(CorDTO dto){

        return Response
                .status(Response.Status.CREATED)
                .entity(corService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CorDTO dto){
        corService.update(id, dto);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        corService.delete(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
