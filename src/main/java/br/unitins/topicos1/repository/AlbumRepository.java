package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Album;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlbumRepository implements PanacheRepository<Album>{
    public List<Album> findByName(String nome){
        return find("nome LIKE ?1", "%"+nome, "%").list();
    }

    
}
