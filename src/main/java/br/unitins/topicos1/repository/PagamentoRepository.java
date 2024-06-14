package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento>{
    
    public List<Pagamento> findByCliente(Long idCliente) {
        return find("cliente.id = ?1", idCliente).list();
    }

    public List<Pagamento> findByPedido(Long idPedido){
        return find("pedido.id = ?1", idPedido).list();
    }
    
}
