package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.MaterialDTO;
import br.unitins.topicos1.dto.MaterialResponseDTO;

import java.util.List;

public interface MaterialService {
    public List<MaterialResponseDTO> getAll();
    public MaterialResponseDTO getById(Long id);
    public List<MaterialResponseDTO> getByNome(String nome);
    public MaterialResponseDTO create(MaterialDTO dto);
    public void update(Long id, MaterialDTO dto);
    public void delete(Long id);
}
