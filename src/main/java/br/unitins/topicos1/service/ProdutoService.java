package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ProdutoResponseDTO;

public interface ProdutoService {

    public List<ProdutoResponseDTO> findAll();
    public ProdutoResponseDTO findById(Long id);
    public List<ProdutoResponseDTO> findByNome(String nome);
    public void updateEstoque(Long id, int qtdComprada);
    
}
