package br.unitins.topicos1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Administrador;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.AdministradorRepository;
import br.unitins.topicos1.repository.PessoaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService{
    
    @Inject
    AdministradorRepository repository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public AdministradorResponseDTO create(@Valid AdministradorDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuarioRepository.persist(usuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setSobreNome(dto.sobreNome());
        pessoa.setCpf(dto.cpf());
        pessoa.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pessoa.setUsuario(usuario);
        pessoaRepository.persist(pessoa);

        Administrador administrador = new Administrador();
        administrador.setPessoa(pessoa);
        repository.persist(administrador);

        return AdministradorResponseDTO.valueOf(administrador);

    }

    @Override
    @Transactional
    public void update(Long id, AdministradorDTO dto) throws ValidationException {
       
        Usuario usuario = repository.findById(id).getPessoa().getUsuario();
        if(usuario != null){
            usuario.setUsername(dto.username());
            // fazer hash da nova senha
            usuario.setSenha(hashService.getHashSenha(dto.senha()));
        } else {
            throw new ValidationException("Cliente inexistente");
        }

        Pessoa pessoa = repository.findById(id).getPessoa();
        if(pessoa != null){
            pessoa.setNome(dto.nome());
            pessoa.setSobreNome(dto.sobreNome());
            pessoa.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
            pessoa.setCpf(dto.cpf());
            pessoa.setUsuario(usuario);
        } else {
            throw new ValidationException("Cliente inexistente");
        }
        
        Administrador adm = repository.findById(id);
        if(adm != null){
            adm.setPessoa(pessoa);
        } else {
            throw new ValidationException("Cliente inexistente");
        }
    }

    @Override
    @Transactional
    public void updateUsuarioPassword(Long id, PasswordUpdateDTO passwordUpdateDTO) throws ValidationException {

        Usuario usuario = usuarioRepository.findById(id);
        Administrador adm = repository.findByUsuarioId(usuario.getId());
        if (usuario == null || adm == null) {
            throw new NotFoundException();
        }

        if(usuario.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.oldPassword()))){
            usuario.setDataAlteracao(LocalDateTime.now());
            usuario.setSenha(hashService.getHashSenha(passwordUpdateDTO.newPassword()));
            usuarioService.update(usuario);
            usuarioRepository.persist(usuario);
        } else {
            throw new ValidationException("Proibido", "SENHA ANTIGA ERRADA");
        }
    }

    @Override
    @Transactional
    public void updateUsuarioUsername(Long id, UsernameUpdateDTO usernameUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        Administrador adm = repository.findByUsuarioId(usuario.getId());
        if (usuario == null || adm == null) {
            throw new InternalError();
        }
        adm.getPessoa().getUsuario().setUsername(usernameUpdateDTO.newUsername());
        usuarioService.update(adm.getPessoa().getUsuario());
        repository.persist(adm);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<AdministradorResponseDTO> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public AdministradorResponseDTO findById(Long id) {
        Administrador cor = repository.findById(id);
        if(cor != null)
            return AdministradorResponseDTO.valueOf(repository.findById(id));
        return null;       
    }

    @Override
    public List<AdministradorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                         .stream()
                         .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public AdministradorResponseDTO findByUsername(String username) {

        Administrador administrador = repository.findByUsername(username);

        if(administrador != null)
            return AdministradorResponseDTO.valueOf(administrador);
        return null;       
    }

    @Override
    public AdministradorResponseDTO findByCpf(String cpf) {

        Administrador administrador = repository.findByCpf(cpf);

        if(administrador != null)
            return AdministradorResponseDTO.valueOf(administrador);
        return null;       
    }

    @Override
    public UsuarioResponseDTO login(String username, String senha) {
        Administrador administrador = repository.findByUsernameAndSenha(username, senha);
        if(administrador != null)
            return UsuarioResponseDTO.valueof(administrador.getPessoa());
        return null;
    }

}
