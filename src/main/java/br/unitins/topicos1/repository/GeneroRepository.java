package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Genero;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeneroRepository implements PanacheRepositoryBase<Genero, Long> {

    public List<Genero> findByName(String nome) {
        return find("nome LIKE ?1", "%" + nome + "%").list();
    }
    
}
