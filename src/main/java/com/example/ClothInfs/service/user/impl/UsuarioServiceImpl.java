package com.example.ClothInfs.service.user.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ClothInfs.model.Usuario;
import com.example.ClothInfs.repository.UsuarioRepository;
import com.example.ClothInfs.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        // Convierte el Iterable a List usando el constructor de ArrayList
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElse(null);
        if (existingUsuario != null) {
            // Verifica si los campos no son nulos antes de actualizar
            if (usuario.getFirstName() != null) {
                existingUsuario.setFirstName(usuario.getFirstName());
            }
            if (usuario.getLastName() != null) {
                existingUsuario.setLastName(usuario.getLastName());
            }
            if (usuario.getPassword() != null) {
                existingUsuario.setPassword(usuario.getPassword());
            }
            if (usuario.getEmail() != null) {
                existingUsuario.setEmail(usuario.getEmail());
            }
            if (usuario.getRoles() != null) {
                existingUsuario.setRoles(usuario.getRoles());
            }

            // Guarda el usuario actualizado en el repositorio
            return usuarioRepository.save(existingUsuario);
        } else {
            // Manejar el caso en que el usuario no exista
            return null;
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetailsService usuarioDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return usuarioRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
