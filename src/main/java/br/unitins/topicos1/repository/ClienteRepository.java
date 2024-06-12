package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{

    public List<Cliente> findByNome(String nome){
        return find("UPPER(pessoa.nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Cliente findByCpf(String cpf){
        return find("pessoa.cpf = ?1", cpf).firstResult();
    }

    public Cliente findByUsername(String username){
        return find("pessoa.usuario.username = ?1", username).firstResult();
    }

    public Cliente findByUsernameAndSenha(String username, String senha){
        return find("pessoa.usuario.username = ?1 AND pessoa.usuario.senha = ?2", username, senha).firstResult();
    }
    
}
