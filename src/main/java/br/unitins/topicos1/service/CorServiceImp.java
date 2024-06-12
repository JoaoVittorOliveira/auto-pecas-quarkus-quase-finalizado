package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.model.Cor;
import br.unitins.topicos1.repository.CorRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class CorServiceImp implements CorService{
    @Inject
    CorRepository corRepository;

    @Override
    public List<CorResponseDTO> getAll() {
        return corRepository
                .listAll()
                .stream()
                .map(Cor -> CorResponseDTO.valueOf(Cor)).toList();
    }

    @Override
    public CorResponseDTO getById(Long id) {
        verificaId(id);
        return CorResponseDTO.valueOf(corRepository
                .findById(id));
    }

    @Override
    public List<CorResponseDTO> getByNome(String nome) {
        return corRepository
                .findByNome(nome)
                .stream()
                .map(Cor -> CorResponseDTO.valueOf(Cor))
                .toList();
    }

    @Override
    public CorResponseDTO getByCodigo(String codigo) {
        return CorResponseDTO.valueOf(corRepository.findByCodigo(codigo));
    }

    @Override
    @Transactional
    public CorResponseDTO create(@Valid CorDTO dto) {
        validarNomeCor(dto.nome());
        validarCodigoCor(dto.codigo());

        Cor cor = new Cor();

        cor.setNome(dto.nome());
        cor.setCodigo(dto.codigo());

        corRepository.persist(cor);
        return CorResponseDTO.valueOf(cor);
    }

    public void validarNomeCor(String nome) {
        Cor cor = corRepository.findByNomeValidation(nome);
        if (cor != null)
            throw  new ValidationException("nome", "O nome '"+ nome +"' já existe.");
    }

    public void validarCodigoCor(String codigo) {
        Cor cor = corRepository.findByCodigoValidation(codigo);
        if (cor != null)
            throw  new ValidationException("nome", "O nome '"+ codigo +"' já existe.");
    }

    @Override
    @Transactional
    public void update(@Valid Long id, CorDTO dto) {
        verificaId(id);
        Cor cor = corRepository.findById(id);

        cor.setNome(dto.nome());
        cor.setCodigo(dto.codigo());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        verificaId(id);
        corRepository.deleteById(id);
    }

    public void verificaId(Long id){
        if(corRepository.findById(id) == null) {
            throw new ValidationException("id", "Valor não encontrado.");
        }
    }
}
