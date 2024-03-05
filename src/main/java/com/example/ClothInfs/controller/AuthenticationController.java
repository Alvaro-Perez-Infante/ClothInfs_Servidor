package com.example.ClothInfs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ClothInfs.dto.request.SignUpRequest;
import com.example.ClothInfs.dto.request.SigninRequest;
import com.example.ClothInfs.dto.response.user.JwtAuthenticationResponse;
import com.example.ClothInfs.service.user.AuthenticationService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador para la autenticación de usuarios.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Maneja las solicitudes de registro de usuarios.
     *
     * @param request La solicitud de registro de usuario
     * @return ResponseEntity con la respuesta de autenticación JWT
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    /**
     * Maneja las solicitudes de inicio de sesión de usuarios.
     *
     * @param request La solicitud de inicio de sesión de usuario
     * @return ResponseEntity con la respuesta de autenticación JWT
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
