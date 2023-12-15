package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Gravadora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GravadoraRepository implements PanacheRepository<Gravadora> {

    public List<Gravadora> findByName(String nome){
        return find("nome LIKE ?1", "%"+nome+ "%").list();
    }
}
