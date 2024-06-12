package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Injecao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InjecaoRepository implements PanacheRepository<Injecao> {
    public List<Injecao> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Injecao> findByCodigo(String codigo) {
        return find("UPPER(codigo) LIKE ?1", "%" + codigo.toUpperCase() + "%").list();
    }

    public List<Injecao> findByTipoCombustivel(String tipoCombustivel) {
        return find("UPPER(tipoCombustivel) LIKE ?1", "%" + tipoCombustivel.toUpperCase() + "%").list();
    }

    public Injecao findByNomeValidation(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}