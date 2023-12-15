package br.unitins.topicos1.resource;

import br.unitins.topicos1.application.Result;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({ "ADMIN", "USER" })
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
        }
        return Response
        .status(Status.NOT_FOUND)
        .entity(result)
        .build();   
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "ADMIN", "USER" })
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
            Result result = new Result(e.getMessage(), "404");
            return Response
            .status(Status.NOT_FOUND)
            .entity(result)
            .build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "ADMIN"})
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }


    @GET
    @RolesAllowed({ "ADMIN" })
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }    

    

    @GET
    @RolesAllowed({ "ADMIN" })
    @Path("/search/login/{login}")
    public Response findByLogin(@PathParam("login") String login){
        return Response.ok(service.findByLogin(login)).build();
    }
}
