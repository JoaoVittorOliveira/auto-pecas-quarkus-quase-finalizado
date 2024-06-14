package br.unitins.topicos1.service;

import java.util.List;

import org.hibernate.Hibernate;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPagamentoPedido;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService{

    @Inject
    public PagamentoRepository repository;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO create(@Valid PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento();
        Cliente cliente = clienteRepository.findById(dto.idCliente());
        Pedido pedido = pedidoRepository.findById(dto.idPedido());
        if(cliente != null && pedido != null){
            pagamento.setCliente(cliente);
            pagamento.setPedido(pedido);

            pagamento.setValorPago(dto.valorPago());
            repository.persist(pagamento);

            if(pagamento.getValorPago().compareTo(pedido.getValorTotal()) >= 0){
                if(pedido.getStatusPagamentoPedido() != StatusPagamentoPedido.PAGO){
                    pedido.setStatusPagamentoPedido(StatusPagamentoPedido.PAGO);
                    pagamento.setTroco(pagamento.getValorPago() - pedido.getValorTotal());
                } else {
                    // pedido já pago
                    throw new ValidationException("Erro no pagamento", "Pedido já pago");
                }
            } else {
                // valor insuficiente (pagamento a vista)
                throw new ValidationException("Erro no pagamento", "Valor insuficiente");
            }

            // para resolver bug de coleção lazy-loaded(nao inicializada antes de finalizar o método)
            Hibernate.initialize(pedido.getItens());

            return PagamentoResponseDTO.valueOf(pagamento);
        }
        return null;
    }

    @Transactional
    public void updateStatusPedido(Long idPagamento){
        Pagamento pagamento = repository.findById(idPagamento);
        if(pagamento != null){
            Pedido pedido = pagamento.getPedido();
            if(pagamento.getValorPago() >= pedido.getValorTotal()){
                if(pagamento.getPedido().getStatusPagamentoPedido().equals(pedido.getStatusPagamentoPedido())){
                    pedido.setStatusPagamentoPedido(StatusPagamentoPedido.PAGO);
                }
            } else {
                // valor insuficiente (pagamento a vista)
                throw new ValidationException("Erro no pagamento", "Valor insuficiente");
            }
        } else {
            // pagamento não existe
            throw new ValidationException("Erro no processamento", "pagamento nao existe");
        }
    }

    @Override
    public List<PagamentoResponseDTO> findAll() {
        return repository
        .listAll()
        .stream()
        .map(e -> PagamentoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PagamentoResponseDTO> findByCliente(Long idCliente) {
        List<PagamentoResponseDTO> lista = repository.findByCliente(idCliente)
                                        .stream()
                                        .map(e -> PagamentoResponseDTO.valueOf(e)).toList();
        if(lista != null)
            return lista;
        return null; 
    }

    @Override
    public List<PagamentoResponseDTO> findByPedido(Long idPedido) {
        List<PagamentoResponseDTO> lista = repository.findByPedido(idPedido)
                                        .stream()
                                        .map(e -> PagamentoResponseDTO.valueOf(e)).toList();
        if(lista != null)
            return lista;
        return null; 
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento != null)
            return PagamentoResponseDTO.valueOf(pagamento);
        return null;
    }
    
}
