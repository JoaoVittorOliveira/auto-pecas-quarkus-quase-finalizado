package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa>{
    
}
