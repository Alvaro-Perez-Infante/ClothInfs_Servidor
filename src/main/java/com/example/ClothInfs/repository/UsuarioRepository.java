package com.example.ClothInfs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ClothInfs.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	 Optional<Usuario> findByEmail(String email);
	    Optional<Usuario> findById(Long id);
	    Boolean existsByEmail(String email);
	
}