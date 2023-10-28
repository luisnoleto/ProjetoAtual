package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.application.Result;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOG = Logger.getLogger(AlbumResource.class);

    @Inject
    UsuarioService service;

    @POST
    public Response insert(UsuarioDTO dto) {
        Result result = null;

        try{
            LOG.infof("Usuario Criado com sucesso: %s", dto.nome());

            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } 
        catch (ConstraintViolationException e) {
            
            LOG.infof("Erro ao inserir o usuario.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } 
        catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result =   result = new Result(e.getMessage(), "404", false);
        }
        return Response
        .status(Status.NOT_FOUND)
        .entity(result)
        .build();   
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        try{
        LOG.infof("Atualizando o usuario: %s", dto.nome());
        service.update(dto, id);
        return Response.noContent().build();
        }
        catch (ConstraintViolationException e) {
            LOG.infof("Erro ao atualizar o usuario.");
            LOG.debug(e.getMessage());
            Result result = new Result(e.getConstraintViolations());
            return Response
            .status(Status.NOT_FOUND)
            .entity(result)
            .build();
        } 
        catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            Result result =   result = new Result(e.getMessage(), "404", false);
            return Response
            .status(Status.NOT_FOUND)
            .entity(result)
            .build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @GET
    @Path("/search/login/{login}")
    public List<UsuarioResponseDTO> findByLogin(@PathParam("login") String login) {
        return service.findByLogin(login);
    }
}
