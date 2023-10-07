package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Artista;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


@ApplicationScoped
public class ArtistaRepository implements PanacheRepository<Artista>{
        public List<Artista> findByName(String nome){
        return find("nome LIKE ?1", "%"+nome, "%").list();
    }
}
