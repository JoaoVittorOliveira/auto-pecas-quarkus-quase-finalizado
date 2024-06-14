package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.FormatoDTO;
import br.unitins.topicos1.dto.FormatoResponseDTO;

import java.util.List;

public interface FormatoService {
    public List<FormatoResponseDTO> getAll();
    public FormatoResponseDTO getById(Long id);
    public List<FormatoResponseDTO> getByDescricaoFormato(String descricao);
    public FormatoResponseDTO create(FormatoDTO dto);
    public void update(Long id, FormatoDTO dto);
    public void delete(Long id);
}
