package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;

import java.util.List;

public interface CorService {
    public List<CorResponseDTO> getAll();
    public CorResponseDTO getById(Long id);
    public List<CorResponseDTO> getByNome(String nome);
    public CorResponseDTO getByCodigo(String codigo);
    public CorResponseDTO create(CorDTO dto);
    public void update(Long id, CorDTO dto);
    public void delete(Long id);
}
