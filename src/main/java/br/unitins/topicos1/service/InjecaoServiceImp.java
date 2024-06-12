package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.InjecaoDTO;
import br.unitins.topicos1.dto.InjecaoResponseDTO;
import br.unitins.topicos1.model.Injecao;
import br.unitins.topicos1.repository.InjecaoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InjecaoServiceImp implements InjecaoService{
    @Inject
    InjecaoRepository injecaoRepository;

    @Override
    public List<InjecaoResponseDTO> getAll() {
        return injecaoRepository
                .listAll()
                .stream()
                .map(injecao -> InjecaoResponseDTO.valueOf(injecao))
                .toList();
    }

    @Override
    public InjecaoResponseDTO getById(Long id) {
        verificaId(id);
        return InjecaoResponseDTO.valueOf(injecaoRepository
                .findById(id));
    }

    @Override
    public List<InjecaoResponseDTO> getByNome(String nome) {
        return injecaoRepository
                .findByNome(nome)
                .stream()
                .map(Injecao -> InjecaoResponseDTO.valueOf(Injecao))
                .toList();
    }

    @Override
    public List<InjecaoResponseDTO> getByCodigo(String codigo) {
        return injecaoRepository
                .findByCodigo(codigo)
                .stream()
                .map(Injecao -> InjecaoResponseDTO.valueOf(Injecao))
                .toList();
    }

    @Override
    public List<InjecaoResponseDTO> getByTipoCombustivel(String tipoCombustivel) {
        return injecaoRepository
                .findByTipoCombustivel(tipoCombustivel)
                .stream()
                .map(Injecao -> InjecaoResponseDTO.valueOf(Injecao))
                .toList();
    }

    @Override
    @Transactional
    public InjecaoResponseDTO create(@Valid InjecaoDTO dto) {
        validarNomeInjecao(dto.nome());

        Injecao injecao = new Injecao();

        injecao.setNome(dto.nome());
        injecao.setCodigo(dto.codigo());
        injecao.setEstoque(dto.estoque());
        injecao.setPreco(dto.preco());
        injecao.setTipoCombustivel(dto.tipoCombustivel());

        injecaoRepository.persist(injecao);
        return InjecaoResponseDTO.valueOf(injecao);
    }

    public void validarNomeInjecao(String nome) {
        Injecao injecao = injecaoRepository.findByNomeValidation(nome);
        if (injecao != null)
            throw  new ValidationException("nome", "O nome '"+ nome +"' já existe.");
    }

    @Override
    @Transactional
    public void update(@Valid Long id, InjecaoDTO dto) {
        verificaId(id);
        Injecao injecao = injecaoRepository.findById(id);


        injecao.setNome(dto.nome());
        injecao.setCodigo(dto.codigo());
        injecao.setTipoCombustivel(dto.tipoCombustivel());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        verificaId(id);
        injecaoRepository.deleteById(id);
    }

    public void verificaId(Long id){
        if(injecaoRepository.findById(id) == null) {
            throw new ValidationException("id", "Valor não encontrado.");
        }
    }
}
