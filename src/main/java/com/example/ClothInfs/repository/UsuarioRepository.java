package com.example.ClothInfs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ClothInfs.model.Usuario;

/**
 * Repositorio para la entidad Usuario que proporciona métodos CRUD.
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    /**
     * Encuentra un usuario por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario.
     * @return Un Optional que contiene el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Encuentra un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return Un Optional que contiene el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findById(Long id);

    /**
     * Verifica si existe un usuario con la dirección de correo electrónico especificada.
     *
     * @param email La dirección de correo electrónico a verificar.
     * @return Verdadero si existe un usuario con la dirección de correo electrónico especificada, falso de lo contrario.
     */
    Boolean existsByEmail(String email);
}
