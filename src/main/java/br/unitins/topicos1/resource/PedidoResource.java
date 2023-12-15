package br.unitins.topicos1.resource;
import java.util.List;

import org.jboss.logging.Logger;
import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.service.ArtistaService;
import br.unitins.topicos1.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
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

    
@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;
    
    private static final Logger LOG = Logger.getLogger(PedidoResource.class);
    private Result result;

    @POST
    //@Transactional
    public Response insert(@Valid ItemPedidoDTO dto){
        LOG.infof("Inserindo um novo peido: %s", dto.idProduto());
        try {
            LOG.infof("Inserido o novo pedido");
            PedidoResponseDTO ip = service.insert(Long.parseLong("1"), dto);
            System.out.println("ID do item pedido: " + ip.id());
            return Response.status(Status.CREATED).entity(ip).build();

        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao inserir o artista.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long idUsuario, Long idPedido) throws IllegalArgumentException{
        try{
            service.delete(idUsuario, idPedido);
            LOG.infof("Deletado o pedido: %d", idPedido);
            return Response.status(Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
        
    }


    

}
