package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.VolanteDTO;
import br.unitins.topicos1.dto.VolanteResponseDTO;
import br.unitins.topicos1.model.Volante;
import br.unitins.topicos1.repository.*;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@ApplicationScoped
public class VolanteServiceImp implements VolanteService{
    @Inject
    VolanteRepository volanteRepository;
    @Inject
    CorRepository corRepository;
    @Inject
    MaterialRepository materialRepository;
    @Inject
    FormatoRepository formatoRepository;

    @Override
    public List<VolanteResponseDTO> getAll() {
        return volanteRepository
                .findAll()
                .stream()
                .map(volante -> VolanteResponseDTO.valueOf(volante))
                .toList();
    }

    @Override
    public VolanteResponseDTO getById(Long id) {
        verificaId(id);

        return VolanteResponseDTO.valueOf(volanteRepository.findById(id));
    }

    @Override
    public List<VolanteResponseDTO> getByNome(String nome) {
        return volanteRepository
                .findByNome(nome)
                .stream()
                .map(volante -> VolanteResponseDTO.valueOf(volante))
                .toList();
    }

    @Override
    public VolanteResponseDTO getByCodigo(String codigo) {
        return  VolanteResponseDTO.valueOf(volanteRepository.findByCodigo(codigo));
    }

    @Override
    @Transactional
    public VolanteResponseDTO create(@Valid VolanteDTO dto) {
        validarNomeVolante(dto.nome());
        validarCodigoVolante(dto.codigo());

        Volante volante = new Volante();

        volante.setNome(dto.nome());
        volante.setCodigo(dto.codigo());
        volante.setEstoque(dto.estoque());
        volante.setPreco(dto.preco());
        volante.setDiametro(dto.diametro());
        volante.setCor(corRepository.findById(dto.idCor()));
        volante.setFormato(formatoRepository.findById(dto.idFormato()));
        volante.setMateiral(materialRepository.findById(dto.idMaterial()));

        volanteRepository.persist(volante);
        return VolanteResponseDTO.valueOf(volante);
    }

    public void validarNomeVolante(String nome) {
        Volante volante = volanteRepository.findByNomeValidation(nome);
        if (volante != null)
            throw  new ValidationException("nome", "O nome '"+ nome +"' já existe.");
    }

    public void validarCodigoVolante(String codigo) {
        Volante volante = volanteRepository.findByCodigo(codigo);
        if (volante != null)
            throw  new ValidationException("nome", "O nome '"+ codigo +"' já existe.");
    }

    @Override
    @Transactional
    public void update(Long id, VolanteDTO dto) {
        verificaId(id);

        Volante volante = volanteRepository.findById(id);

        volante.setNome(dto.nome());
        volante.setCodigo(dto.codigo());
        volante.setDiametro(dto.diametro());
        volante.setCor(corRepository.findById(dto.idCor()));
        volante.setFormato(formatoRepository.findById(dto.idFormato()));
        volante.setMateiral(materialRepository.findById(dto.idMaterial()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        verificaId(id);

        volanteRepository.deleteById(id);
    }

    public void verificaId(Long id){
        if(volanteRepository.findById(id) == null){
            throw new ValidationException("id", "Valor nao encontrado.");
        }
    }
}
