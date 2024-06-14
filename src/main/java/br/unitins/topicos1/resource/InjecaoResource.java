package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

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

    private static final Logger LOG = Logger.getLogger(InjecaoResource.class);

    @GET
    public Response getAll(){
        LOG.info("Executando getAll");
        return Response.ok(injecaoService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        LOG.infof("Executando o metodo getById. Id: %s", id.toString());
        return Response.ok(injecaoService.getById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response getByNome(@PathParam("nome") String nome){
        LOG.infof("Executando o metodo getByNome. Nome: %s", nome);
        return Response.ok(injecaoService.getByNome(nome)).build();
    }

    @GET
    @Path("/search/codigo/{codigo}")
    public Response getByCodigo(@PathParam("codigo") String codigo){
        LOG.infof("Executando o metodo getByCodigo. Id: %s", codigo.toString());
        return Response.ok(injecaoService.getByCodigo(codigo)).build();
    }

    @GET
    @Path("/search/tipoCombustivel/{tipoCombustivel}")
    public Response getByTipoCombustivel(@PathParam("tipoCombustivel") String tipoCombustivel){
        LOG.infof("Executando o metodo getByTipoCombustivel. tipoCombustivel: %s", tipoCombustivel.toString());
        return Response.ok(injecaoService.getByTipoCombustivel(tipoCombustivel)).build();
    }

    @POST
    public Response create(InjecaoDTO dto){
        LOG.info("Executando create");
        LOG.debugf("DTO: %s", dto);
        return Response
                .status(Response.Status.CREATED)
                .entity(injecaoService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, InjecaoDTO dto){
        LOG.info("Executando update");
        LOG.debugf("New DTO: %s", dto);
        injecaoService.update(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.info("Executando delete");
        LOG.infof("ID informado: "+id);
        injecaoService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
