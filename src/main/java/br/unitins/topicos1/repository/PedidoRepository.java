package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findAll(String login) {
        return find("usuario.login = ?1", login).list();
    }
    
    public List<Pedido> findAll(Long idUsuario) {
        return find("usuario.id = ?1", idUsuario).list();
    }


    public List<Pedido> findByUsuarioPedidos (Usuario usuario) {
        if (usuario == null)
            return null;
        
        return find("FROM Pedido WHERE usuario = ?1 " , usuario).list();
    }

    public Pedido findByUsuarioPedido(String login, Long idPedido) {
        if (login == null || idPedido == null)
            return null;
        
        return find("usuario.login = ?1 and id = ?2", login, idPedido).firstResult();
    }

}
