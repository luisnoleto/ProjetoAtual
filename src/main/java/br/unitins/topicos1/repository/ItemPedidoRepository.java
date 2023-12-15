package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Album;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {
    
    public ItemPedido findByAlbum (Album album) {

        if (album == null)
            return null;

        return find("FROM ItemPedido WHERE album = ?1", album).firstResult();
    }
}
