package com.example.ClothInfs.service.user;

import com.example.ClothInfs.dto.request.SignUpRequest;
import com.example.ClothInfs.dto.request.SigninRequest;
import com.example.ClothInfs.dto.response.user.JwtAuthenticationResponse;

/**
 * Interfaz para el servicio de autenticación.
 */
public interface AuthenticationService {

    /**
     * Método para registrar un nuevo usuario.
     * 
     * @param request los datos de registro del usuario
     * @return la respuesta de autenticación JWT
     */
    JwtAuthenticationResponse signup(SignUpRequest request);

    /**
     * Método para iniciar sesión y obtener un token JWT.
     * 
     * @param request los datos de inicio de sesión del usuario
     * @return la respuesta de autenticación JWT
     */
    JwtAuthenticationResponse signin(SigninRequest request);
}
