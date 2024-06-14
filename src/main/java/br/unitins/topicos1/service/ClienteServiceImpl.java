package br.unitins.topicos1.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.ClienteRepository;
import br.unitins.topicos1.repository.PessoaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationError;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{
    
    @Inject
    ClienteRepository repository;

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
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        // fazer hash da senha
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuarioRepository.persist(usuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setSobreNome(dto.sobreNome());
        pessoa.setCpf(dto.cpf());
        pessoa.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pessoa.setUsuario(usuario);
        pessoaRepository.persist(pessoa);

        Cliente cliente = new Cliente();
        cliente.setSaldo(0d);
        cliente.setEmail(dto.email());
        cliente.setPessoa(pessoa);
        repository.persist(cliente);

        return ClienteResponseDTO.valueOf(cliente);

    }

    @Override
    @Transactional
    public void update(Long id, ClienteDTO dto) throws ValidationException {
       
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
        
        Cliente cliente = repository.findById(id);

        if(cliente != null){
            cliente.setSaldo(0d);
            cliente.setPessoa(pessoa);
        } else {
            throw new ValidationException("Cliente inexistente");
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Transactional
    @Override
    public List<ClienteResponseDTO> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cor = repository.findById(id);
        if(cor != null)
            return ClienteResponseDTO.valueOf(repository.findById(id));
        return null;       
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                         .stream()
                         .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public ClienteResponseDTO findByUsername(String username) {

        Cliente cliente = repository.findByUsername(username);

        if(cliente != null)
            return ClienteResponseDTO.valueOf(cliente);
        return null;       
    }

    @Override
    public ClienteResponseDTO findByCpf(String cpf) {

        Cliente cliente = repository.findByCpf(cpf);

        if(cliente != null)
            return ClienteResponseDTO.valueOf(cliente);
        return null;       
    }

    @Override
    public UsuarioResponseDTO login(String username, String senha) {
        Cliente cliente = repository.findByUsernameAndSenha(username, senha);
        if(cliente != null)
            return UsuarioResponseDTO.valueof(cliente.getPessoa());
        return null;
    }

    @Override
    @Transactional
    public void updateUsuarioPassword(Long id, PasswordUpdateDTO passwordUpdateDTO) throws ValidationException {

        Usuario usuario = usuarioRepository.findById(id);
        Cliente cliente = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || cliente == null) {
            throw new InternalError();
        }

        if(usuario.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.oldPassword()))){
            usuario.setDataAlteracao(LocalDateTime.now());
            usuario.setSenha(hashService.getHashSenha(passwordUpdateDTO.newPassword()));
            usuarioService.update(usuario);
            usuarioRepository.persist(usuario);
        } else {
            throw new ValidationException("Senha errada", "SENHA ANTIGA ERRADA");
        }
    }

    @Override
    @Transactional
    public void updateUsuarioUsername(Long id, UsernameUpdateDTO usernameUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id);
        Cliente cliente = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || cliente == null) {
            throw new InternalError();
        }
        cliente.getPessoa().getUsuario().setUsername(usernameUpdateDTO.newUsername());
        usuarioService.update(cliente.getPessoa().getUsuario());
        repository.persist(cliente);
    }

}
