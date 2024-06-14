package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import jakarta.validation.Valid;

public interface PagamentoService {
    public PagamentoResponseDTO create(@Valid PagamentoDTO dto);
    public PagamentoResponseDTO findById(Long id);
    public List<PagamentoResponseDTO> findAll();
    public List<PagamentoResponseDTO> findByCliente(Long idCliente);
    public List<PagamentoResponseDTO> findByPedido(Long idCliente);
}
