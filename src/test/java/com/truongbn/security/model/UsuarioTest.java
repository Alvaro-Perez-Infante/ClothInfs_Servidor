package com.truongbn.security.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.ClothInfs.model.Role;
import com.example.ClothInfs.model.Usuario;


public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testGetAuthorities() {
        // Configuración de roles para el usuario
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        roles.add(Role.ROLE_USER);
        usuario.setRoles(roles);

        // Comprobación de autoridades
        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }


    @Test
    void testIsAccountNonExpired() {
        // La cuenta no caduca
        assertTrue(usuario.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        // La cuenta no está bloqueada
        assertTrue(usuario.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        // Las credenciales no caducan
        assertTrue(usuario.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        // La cuenta está habilitada
        assertTrue(usuario.isEnabled());
    }

    @Test
    void testGetUsername() {
        // Configuración de email para el usuario
        usuario.setEmail("test@example.com");

        // Comprobación del nombre de usuario
        assertTrue(usuario.getUsername().equals("test@example.com"));
    }

    @Test
    void testGetPassword() {
        // Configuración de contraseña para el usuario
        usuario.setPassword("password123");

        // Comprobación de la contraseña
        assertTrue(usuario.getPassword().equals("password123"));
    }
}
