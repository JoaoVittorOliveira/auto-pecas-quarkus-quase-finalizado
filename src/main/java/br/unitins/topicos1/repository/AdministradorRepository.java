package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Administrador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador>{

    public List<Administrador> findByNome(String nome){
        return find("UPPER(pessoa.nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Administrador findByCpf(String cpf){
        return find("pessoa.cpf = ?1", cpf).firstResult();
    }

    public Administrador findByUsername(String username){
        return find("pessoa.usuario.username = ?1", username).firstResult();
    }

    public Administrador findByUsernameAndSenha(String username, String senha){
        return find("pessoa.usuario.username = ?1 AND pessoa.usuario.senha = ?2", username, senha).firstResult();
    }

    public Administrador findByUsuarioId(Long id){
        return find("pessoa.usuario.id = ?1", id).firstResult();
    }
    
}
