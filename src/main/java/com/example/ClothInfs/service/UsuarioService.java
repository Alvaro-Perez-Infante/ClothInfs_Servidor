package com.example.ClothInfs.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ClothInfs.model.Usuario;

/**
 * Interfaz que define los métodos para el servicio relacionado con la entidad Usuario.
 */
public interface UsuarioService {

    /**
     * Obtiene el servicio de detalles de usuario.
     *
     * @return El servicio de detalles de usuario.
     */
    UserDetailsService usuarioDetailsService();

    /**
     * Obtiene todos los usuarios.
     *
     * @return Una lista de todos los usuarios.
     */
    List<Usuario> getAllUsuarios();

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario encontrado.
     */
    Usuario getUsuarioById(Long id);

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuario El usuario a agregar.
     * @return El usuario agregado.
     */
    Usuario addUsuario(Usuario usuario);

    /**
     * Actualiza un usuario existente.
     *
     * @param id      El identificador del usuario a actualizar.
     * @param usuario El usuario actualizado.
     * @return El usuario actualizado.
     */
    Usuario updateUsuario(Long id, Usuario usuario);

    /**
     * Elimina un usuario por su identificador.
     *
     * @param id El identificador del usuario a eliminar.
     */
    void deleteUsuario(Long id);
}
