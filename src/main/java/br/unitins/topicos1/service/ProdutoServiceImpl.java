package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository repository;

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return repository.findAll().list()
                    .stream()
                    .map(p -> ProdutoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = repository.findById(id);
        if(produto != null)
            return ProdutoResponseDTO.valueOf(produto);
        return null;
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {

        List<ProdutoResponseDTO> lista = repository.findByNome(nome)
        .stream()
        .map(e -> ProdutoResponseDTO.valueOf(e)).toList();

        if(lista.size() != 0)
            return lista;
        return null;
    }

    @Override
    @Transactional
    public void updateEstoque(Long id, int qtdComprada) {
        // FAZER PATH DE ESTOQUE PRODUTO
        Produto produto = repository.findById(id);
        produto.setEstoque(produto.getEstoque() - qtdComprada);
    }
    
}
