package br.unitins.topicos1.service;


import br.unitins.topicos1.dto.FormatoDTO;
import br.unitins.topicos1.dto.FormatoResponseDTO;
import br.unitins.topicos1.model.Formato;
import br.unitins.topicos1.repository.FormatoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@ApplicationScoped
public class FormatoServiceImp implements FormatoService{
    @Inject
    FormatoRepository formatoRepository;


    @Override
    public List<FormatoResponseDTO> getAll() {
        return formatoRepository
                .findAll()
                .stream()
                .map(formato -> FormatoResponseDTO.valueOf(formato))
                .toList();
    }

    @Override
    public FormatoResponseDTO getById(Long id) {
        verifidaId(id);

        return FormatoResponseDTO.valueOf(formatoRepository.findById(id));
    }

    @Override
    public List<FormatoResponseDTO> getByDescricaoFormato(String descricao) {
        return formatoRepository
                .findByDescricaoFormato(descricao)
                .stream()
                .map(formato -> FormatoResponseDTO.valueOf(formato))
                .toList();
    }

    @Override
    @Transactional
    public FormatoResponseDTO create(@Valid FormatoDTO dto) {
        createValidation(dto);
        Formato formato = new Formato();

        formato.setDescricaoFormato(dto.descricaoFormato());
        formatoRepository.persist(formato);
        return FormatoResponseDTO.valueOf(formato);
    }

    public void createValidation(FormatoDTO dto){
        Formato formato = formatoRepository.findByDescricaoFormatoValidation(dto.descricaoFormato());
        if(formato != null){
            throw new ValidationException("formato", "Descricao de formato já existe.");
        }
    }

    @Override
    @Transactional
    public void update(@Valid Long id, FormatoDTO dto) {
        verifidaId(id);

        Formato formato = formatoRepository.findById(id);

        formato.setDescricaoFormato(dto.descricaoFormato());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        verifidaId(id);

        formatoRepository.deleteById(id);
    }

    public void verifidaId(Long id){
        if(formatoRepository.findById(id) == null){
            throw new ValidationException("id","Valor nao encontrado.");
        }
    }
}
