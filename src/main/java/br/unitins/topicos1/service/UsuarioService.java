package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Usuario;

public interface UsuarioService {
    public Usuario findByUsername(String username);
    public void update(Usuario usuario);
}
