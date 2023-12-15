package br.unitins.topicos1.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.model.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {
    
    public List<Municipio> findByNome (String nome) {

        if (nome == null)
            return null;

        return find("FROM Municipio WHERE UNACCENT(UPPER(nome)) LIKE UNACCENT(?1)", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Municipio> findByEstado (Estado estado) {

        if (estado == null)
            return null;

        return find("FROM Municipio WHERE estado = ?1", estado).list();
    }
}
