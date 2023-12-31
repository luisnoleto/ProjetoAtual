package br.unitins.topicos1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.topicos1.application.Result;
import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.service.EstadoService;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService estadoService;

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @GET
    public List<EstadoResponseDTO> getAll() {
        LOG.infof("Buscando todos os estados");

        return estadoService.getAll();
    }

    @GET
    @Path("/{id}")
    public EstadoResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando estados por ID. ", id);
    
        return estadoService.getById(id);
    }

    @POST
    public Response insert(EstadoDTO estadoDto) {
        LOG.infof("Inserindo um estado: %s", estadoDto.nome());
        Result result = null;
        try {
            LOG.infof("Produto inserido na lista Desejo.");

            return Response
                    .status(Status.CREATED) // 201
                    .entity(estadoService.insert(estadoDto))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um estado.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());

        }
        return Response
                .status(Status.NOT_FOUND)
                .entity(result)
                .build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EstadoDTO estadoDto) {
        Result result = null;
        try {
            estadoService.update(id, estadoDto);
            LOG.infof("Estado (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar um Estado. ", id, e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());

        }
        return Response
                .status(Status.NOT_FOUND)
                .entity(result)
                .build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {
        try {
            estadoService.delete(id);
            LOG.infof("Estado excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar um estado: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/count")
    public Long count() {
        LOG.infof("Contando todos os estados");
        return estadoService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    public List<EstadoResponseDTO> getByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando estado pelo  nome. ", nome);
       
        return estadoService.getByNome(nome);
    }

    @GET
    @Path("/searchBySigla/{sigla}")
    public List<EstadoResponseDTO> getBySigla(@PathParam("sigla") String sigla) {
        LOG.infof("Buscando estado pela sigla. ", sigla);
       
        return estadoService.getBySigla(sigla);
    }
}
