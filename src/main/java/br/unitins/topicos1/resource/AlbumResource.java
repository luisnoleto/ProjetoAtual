package br.unitins.topicos1.resource;

import java.io.IOException;
import java.util.List;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;
import br.unitins.topicos1.form.AlbumImageForm;
import br.unitins.topicos1.service.AlbumService;
import br.unitins.topicos1.service.FileService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.topicos1.application.Result;

    
@Path("/albuns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbumResource {

    @Inject
    AlbumService service;
    
    @Inject
    FileService fileService;
    
    private static final Logger LOG = Logger.getLogger(AlbumResource.class);
    private Result result;

    @POST
    public Response insert(@Valid AlbumDTO dto){
        LOG.infof("Inserindo um novo album: %s", dto.nome());
        try {
            LOG.infof("Inserido o novo album");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();

        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao inserir o album.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")   
    public Response update(AlbumDTO dto, @PathParam("id") Long id){
        try{
            service.update(dto, id);
            LOG.infof("Atualizado o album: %s", dto.nome());
            return Response.status(Status.OK).build();
        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao atualizar o album.");
            result = new Result(e.getConstraintViolations());
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "400", false);  
        }
         return Response.status(Status.BAD_REQUEST).entity(result).build();
    }


    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response delete(@PathParam("id") Long id)  {
         
        try{
            service.delete(id);
            LOG.infof("Deletado o album: %d", id);
            
            result = new Result("200", "Deletado com sucesso", true);
            return Response.status(Status.OK).build();
        } catch (IllegalArgumentException e) {

            result = new Result(e.getMessage(), "404", false);
            return Response.status(Status.NOT_FOUND).build();
        }   
    }

     @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(Long idAlbum, @PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.downloadProduto(idAlbum, nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @PATCH
@Path("/image/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)  
public Response salvarImagem(
        @MultipartForm AlbumImageForm form,
        @PathParam("id") Long idAlbum) {
    String nomeImagem = "";
    try {
        nomeImagem = fileService.salvarProduto(idAlbum, form.getNomeImagem(), form.getImagem());
        LOG.infof("Imagem salva com sucesso: %s", nomeImagem);

    } catch (IOException e) {
        result = new Result(e.getMessage(), "409", false);
        return Response.status(Status.CONFLICT).entity(result).build();
    }

    service.updateImage(nomeImagem, idAlbum);
    return Response.status(Status.OK).build();
}


    @GET
    public Response findAll(){
        LOG.infof("Buscando todos os albuns");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public AlbumResponseDTO findById(@PathParam("id") Long id) throws NotFoundException{
        LOG.infof("Buscando album por id: %d", id);
        return service.findById(id);
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<AlbumResponseDTO> findByName(@PathParam("nome") String nome)
    
       
       {
        LOG.infof("Buscando album por nome: %s", nome);
        return service.findByName(nome);
    }

}
