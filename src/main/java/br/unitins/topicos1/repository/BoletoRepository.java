package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Boleto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class BoletoRepository implements PanacheRepository<Boleto>{
    public List<Boleto> findAll(String login) {
        return find("usuario.login = ?1", login).list();
    }
}

