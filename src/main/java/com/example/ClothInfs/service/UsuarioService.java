package com.example.ClothInfs.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ClothInfs.model.Usuario;

public interface UsuarioService {

	UserDetailsService usuarioDetailsService();
	
    List<Usuario> getAllUsuarios();

    Usuario getUsuarioById(Long id);

    Usuario addUsuario(Usuario usuario);

    Usuario updateUsuario(Long id, Usuario usuario);

    void deleteUsuario(Long id);

}
