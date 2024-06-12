package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Formato;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FormatoRepository implements PanacheRepository<Formato> {

    public List<Formato> findByDescricaoFormato(String descricao) {
        return find("UPPER(descricaoFormato) LIKE ?1", "%" + descricao.toUpperCase() + "%").list();
    }

    public Formato findByDescricaoFormatoValidation(String descricao) {
        return find("UPPER(descricaoFormato) = ?1", descricao.toUpperCase()).firstResult();
    }
}
