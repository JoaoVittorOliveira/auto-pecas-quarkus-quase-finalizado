package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.FormatoDTO;
import br.unitins.topicos1.service.FormatoServiceImp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/formatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormatoResource {
    @Inject
    FormatoServiceImp formatoService;

    @GET
    public Response getAll(){
        return Response.ok(formatoService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        return Response.ok(formatoService.getById(id)).build();
    }

    @GET
    @Path("/search/{descricaoFormato}")
    public Response getByDescricaoFormato(@PathParam("descricaoFormato") String descricao){
        return Response.ok(formatoService.getByDescricaoFormato(descricao)).build();
    }

    @POST
    public Response create(FormatoDTO dto){
        return Response
                .status(Response.Status.CREATED)
                .entity(formatoService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update (@PathParam("id") Long id, FormatoDTO dto){
        formatoService.update(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        formatoService.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
}
