package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.MaterialDTO;
import br.unitins.topicos1.dto.MaterialResponseDTO;
import br.unitins.topicos1.model.Material;
import br.unitins.topicos1.repository.MaterialRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MaterialServiceImp implements MaterialService{
    @Inject
    MaterialRepository materialRepository;

    @Override
    public List<MaterialResponseDTO> getAll() {
        return materialRepository
                .findAll()
                .stream()
                .map(material -> MaterialResponseDTO.valueOf(material))
                .toList();
    }

    @Override
    public MaterialResponseDTO getById(Long id) {
        verificaId(id);
        return MaterialResponseDTO.valueOf(materialRepository.findById(id));
    }

    @Override
    public List<MaterialResponseDTO> getByNome(String nome) {
        return materialRepository
                .findByNome(nome)
                .stream()
                .map(material -> MaterialResponseDTO.valueOf(material))
                .toList();
    }

    @Override
    @Transactional
    public MaterialResponseDTO create(@Valid MaterialDTO dto) {
        validarNomeMaterial(dto.nome());

        Material material = new Material();

        material.setNome(dto.nome());
        material.setDataCadastro(LocalDateTime.now());

        materialRepository.persist(material);
        return MaterialResponseDTO.valueOf(material);
    }

    public void validarNomeMaterial(String nome){
        Material material = materialRepository.findByNomeValidation(nome);

        if(material != null){
            throw new ValidationException("descricao", "Adescricao:'"+nome+"' já existe.");
        }
    }

    @Override
    @Transactional
    public void update(Long id, MaterialDTO dto) {
        verificaId(id);
        Material material = materialRepository.findById(id);

        material.setNome(dto.nome());
        material.setDataAlteracao(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        verificaId(id);
        materialRepository.deleteById(id);

    }

    public void verificaId(Long id){
        if(materialRepository.findById(id) == null){
            throw new ValidationException("id", "Valor nao encontrado.");
        }
    }
}
