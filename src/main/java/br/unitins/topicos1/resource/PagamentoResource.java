package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.service.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {
    
    @Inject
    public PagamentoService service;

    @POST
    @RolesAllowed("Cliente")
    public Response create(PagamentoDTO dto){
        // if(service.create(dto) != null)
            return Response.ok(service.create(dto)).build();
        // return Response.status(Status.NOT_FOUND).build(); 
    }

    @GET
    // @RolesAllowed("Funcionario")
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    @RolesAllowed("Funcionario")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    // @RolesAllowed("Funcionario")
    @Path("/search/clientes/id/{idCliente}")
    public Response findByCliente( @PathParam("idCliente") Long idCliente ){
        return Response.ok(service.findByCliente(idCliente)).build();
    }

    @GET
    // @RolesAllowed("Funcionario")
    @Path("search/pedidos/id/{idPedido}")
    public Response findByPedido( @PathParam("idPedido") Long idPedido){
        return Response.ok(service.findByPedido(idPedido)).build();
    }

}
