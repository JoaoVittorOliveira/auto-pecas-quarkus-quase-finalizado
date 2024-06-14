package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

public interface AdministradorService {
    
    public AdministradorResponseDTO create(@Valid AdministradorDTO dto);
    public void update(Long id, AdministradorDTO dto) throws ValidationException;
    public void updateUsuarioPassword(Long id, PasswordUpdateDTO passwordUpdateDTO) throws ValidationException;
    public void updateUsuarioUsername(Long id, UsernameUpdateDTO usernameUpdateDTO);
    public boolean delete(Long id);
    public List<AdministradorResponseDTO> findAll();
    public AdministradorResponseDTO findById(Long id);
    public List<AdministradorResponseDTO> findByNome(String nome);
    public AdministradorResponseDTO findByUsername(String username);
    public AdministradorResponseDTO findByCpf(String cpf);
    public UsuarioResponseDTO login(String username, String hashSenha);
}
