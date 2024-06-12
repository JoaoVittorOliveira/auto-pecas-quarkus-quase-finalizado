package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.InjecaoDTO;
import br.unitins.topicos1.service.InjecaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/injecoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InjecaoResource {
    @Inject
    InjecaoService injecaoService;

    @GET
    public Response getAll(){
        return Response.ok(injecaoService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        return Response.ok(injecaoService.getById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response getByNome(@PathParam("nome") String nome){
        return Response.ok(injecaoService.getByNome(nome)).build();
    }

    @GET
    @Path("/search/codigo/{codigo}")
    public Response getByCodigo(@PathParam("codigo") String codigo){
        return Response.ok(injecaoService.getByCodigo(codigo)).build();
    }

    @GET
    @Path("/search/tipoCombustivel/{tipoCombustivel}")
    public Response getByTipoCombustivel(@PathParam("tipoCombustivel") String tipoCombustivel){
        return Response.ok(injecaoService.getByTipoCombustivel(tipoCombustivel)).build();
    }

    @POST
    public Response create(InjecaoDTO dto){

        return Response
                .status(Response.Status.CREATED)
                .entity(injecaoService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, InjecaoDTO dto){
        injecaoService.update(id, dto);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        injecaoService.delete(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
