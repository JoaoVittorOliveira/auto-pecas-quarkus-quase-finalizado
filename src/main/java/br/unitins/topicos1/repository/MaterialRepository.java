package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Material;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MaterialRepository implements PanacheRepository<Material> {

    public List<Material> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Material findByNomeValidation(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}
