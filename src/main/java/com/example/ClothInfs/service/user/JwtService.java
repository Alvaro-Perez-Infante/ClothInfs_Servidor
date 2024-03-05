package com.example.ClothInfs.service.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz para el servicio JWT.
 */
public interface JwtService {

    /**
     * Método para extraer el nombre de usuario del token JWT.
     * 
     * @param token el token JWT
     * @return el nombre de usuario extraído del token
     */
    String extractUserName(String token);

    /**
     * Método para generar un token JWT basado en los detalles del usuario.
     * 
     * @param userDetails los detalles del usuario
     * @return el token JWT generado
     */
    String generateToken(UserDetails userDetails);

    /**
     * Método para verificar si un token JWT es válido para los detalles del usuario proporcionados.
     * 
     * @param token el token JWT
     * @param userDetails los detalles del usuario
     * @return true si el token es válido, false de lo contrario
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
