package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    public List<Cor> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Cor findByCodigo(String codigo) {
        return (Cor) find("UPPER(codigo) LIKE ?1", "%" + codigo.toUpperCase() + "%").firstResult();
    }

    public Cor findByNomeValidation(String nome) {
        return (Cor) find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public Cor findByCodigoValidation(String codigo) {
        return (Cor) find("UPPER(codigo) = ?1", codigo.toUpperCase()).firstResult();
    }
}

