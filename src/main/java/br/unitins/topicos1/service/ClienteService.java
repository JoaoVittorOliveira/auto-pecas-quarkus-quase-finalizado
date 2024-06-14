package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface ClienteService {
    
    public ClienteResponseDTO create(@Valid ClienteDTO dto);
    public void update(Long id, ClienteDTO dto);
    public void updateUsuarioPassword(Long id, PasswordUpdateDTO passwordUpdateDTO);
    public void updateUsuarioUsername(Long id, UsernameUpdateDTO usernameUpdateDTO);
    public boolean delete(Long id);
    public List<ClienteResponseDTO> findAll();
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findByNome(String nome);
    public ClienteResponseDTO findByUsername(String username);
    public ClienteResponseDTO findByCpf(String cpf);
    public UsuarioResponseDTO login(String username, String hashSenha);
}
