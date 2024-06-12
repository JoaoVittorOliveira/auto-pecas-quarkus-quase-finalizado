package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.ProdutoFileServiceImpl;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @Inject
    public ProdutoService service;

    @Inject
    public ProdutoFileServiceImpl fileService;

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        List<ProdutoResponseDTO> lista = service.findByNome(nome);
        if(lista != null)
            return Response.ok(lista).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id){
        return Response.ok(service.findById(id)).build();
    }

    @PATCH
    @Path("/{id}/imagem/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.upload(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        return Response.ok(fileService.download(nomeImagem))
               .header("Content-Disposition", "attachment;filename=" + nomeImagem).build();
    }

    // Novos (fazer test unit):
    // find by descricao
    // find by fornecedor (id)
    // find by marca (id)

}
