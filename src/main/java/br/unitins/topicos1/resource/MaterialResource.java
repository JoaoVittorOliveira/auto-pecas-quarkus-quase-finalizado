package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.MaterialDTO;
import br.unitins.topicos1.service.MaterialService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/materiais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialResource {
    @Inject
    MaterialService materialService;

    @GET
    public Response getAll(){
        return Response.ok(materialService.getAll()).build();
    }

    @Path("/{id}")
    @GET
    public Response getById(@PathParam("id") Long id){
        return Response.ok(materialService.getById(id)).build();
    }

    @Path("/search/nome/{nome}")
    @GET
    public Response getByNome(@PathParam("nome") String nome){
        return Response.ok(materialService.getByNome(nome)).build();
    }

    @POST
    public Response create(MaterialDTO dto){
        return Response
                .status(Response.Status.CREATED)
                .entity(materialService.create(dto))
                .build();
    }

    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") Long id, MaterialDTO dto){
        materialService.update(id, dto);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") Long id){
        materialService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
