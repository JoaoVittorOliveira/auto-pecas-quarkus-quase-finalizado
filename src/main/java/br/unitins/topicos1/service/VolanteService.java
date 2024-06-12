package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.VolanteDTO;
import br.unitins.topicos1.dto.VolanteResponseDTO;

import java.util.List;

public interface VolanteService {
    public List<VolanteResponseDTO> getAll();
    public VolanteResponseDTO getById(Long id);
    public List<VolanteResponseDTO> getByNome(String nome);
    public VolanteResponseDTO getByCodigo(String codigo);
    public VolanteResponseDTO create(VolanteDTO dto);
    public void update(Long id, VolanteDTO dto);
    public void delete(Long id);


}
