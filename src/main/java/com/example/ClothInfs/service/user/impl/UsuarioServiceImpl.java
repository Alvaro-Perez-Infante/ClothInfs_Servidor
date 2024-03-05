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

    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    @Override
    public List<Usuario> getAllUsuarios() {
        // Convierte el Iterable a List usando el constructor de ArrayList
        return StreamSupport.stream(usuarioRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id el ID del usuario a buscar
     * @return el usuario con el ID especificado, o null si no se encuentra
     */
    @Override
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuario el usuario a agregar
     * @return el usuario agregado
     */
    @Override
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id el ID del usuario a actualizar
     * @param usuario el usuario con los nuevos datos
     * @return el usuario actualizado, o null si el usuario no existe
     */
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

    /**
     * Elimina un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar
     */
    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Crea un servicio para obtener detalles de usuario para Spring Security.
     *
     * @return el servicio UserDetailsService
     */
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
