package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.StatusPagamentoPedido;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{

    @Inject
    public PedidoRepository repository;

    @Inject
    public ProdutoRepository produtoRepository;

    @Inject
    public ProdutoService produtoService;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findById(dto.idCliente()));
        pedido.setValorTotal(0d); // vai atualizar no "for"

        List<ItemPedido> itens = new ArrayList<ItemPedido>();

        for(ItemPedidoDTO itemDTO : dto.itens()){

            Produto produto = produtoRepository.findById(itemDTO.idProduto());

            if(produto.getEstoque() >= itemDTO.quantidade_produtos()){
                ItemPedido itemPedido = new ItemPedido();

                itemPedido.setQuantidadeProdutos(itemDTO.quantidade_produtos());
                itemPedido.setPorcentagemDesconto(itemDTO.procentagem_desconto());
                itemPedido.setProduto(produto);

                Double precoDesconto = Double.valueOf(
                                        produto.getPreco() * itemPedido.getQuantidadeProdutos()) -
                                        ((itemDTO.procentagem_desconto()/100) * Double.valueOf(produto.getPreco() * itemPedido.getQuantidadeProdutos()));

                itemPedido.setValor(precoDesconto);

                itens.add(itemPedido);

                // valor total do pedido
                Double valorItemTotal = itemPedido.getValor() * itemPedido.getQuantidadeProdutos();
                pedido.setValorTotal(pedido.getValorTotal() + valorItemTotal);

                // estoque do produto
                produtoService.updateEstoque(itemDTO.idProduto(), itemDTO.quantidade_produtos());
            } else{
                // quantidade insuficiente daquele item
                throw new ValidationException("Estoque Insuficiente", 
                                             "estoque insuficiente do produto: "+produto.getNome()
                                             +"\n"
                                             +"Estoque do produto:"+produto.getEstoque());
            }
        }

        // FAZER PAGAMENTO (VALIDAÇÃO DE SALDO ETC)
        pedido.setStatusPagamentoPedido(StatusPagamentoPedido.NAO_PAGO);

        pedido.setItens(itens);
        repository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return repository
        .listAll()
        .stream()
        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        List<PedidoResponseDTO> lista = repository.findByCliente(idCliente)
                                        .stream()
                                        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
        if(lista != null)
            return lista;
        return null; 
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = repository.findById(id);
        if (pedido != null)
            return PedidoResponseDTO.valueOf(pedido);
        return null;
    }
    
}
