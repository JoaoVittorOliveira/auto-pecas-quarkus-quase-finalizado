package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.InjecaoDTO;
import br.unitins.topicos1.dto.InjecaoResponseDTO;

import java.util.List;

public interface InjecaoService {
    public List<InjecaoResponseDTO> getAll();
    public InjecaoResponseDTO getById(Long id);
    public List<InjecaoResponseDTO> getByNome(String nome);
    public List<InjecaoResponseDTO> getByCodigo(String codigo);
    public List<InjecaoResponseDTO> getByTipoCombustivel(String tipoCombustivel);
    public InjecaoResponseDTO create(InjecaoDTO dto);
    public void update(Long id, InjecaoDTO dto);
    public void delete(Long id);
}
