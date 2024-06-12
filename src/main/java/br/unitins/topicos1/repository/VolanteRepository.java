package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Volante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class VolanteRepository implements PanacheRepository<Volante> {
    public List<Volante> findByNome(String nome) {
        return find("nome LIKE ?1", "%" + nome + "%").list();
    }

    public Volante findByCodigo(String codigo) {
        return find("codigo LIKE ?1", "%" + codigo + "%").firstResult();
    }

    public List<Volante> findByDiametro(float diametro) {
        return find("diametro LIKE ?1", "%" + diametro + "%").list();
    }

    public Volante findByNomeValidation(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}