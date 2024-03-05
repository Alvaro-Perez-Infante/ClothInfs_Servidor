package com.example.ClothInfs.service.user.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ClothInfs.dto.request.SignUpRequest;
import com.example.ClothInfs.dto.request.SigninRequest;
import com.example.ClothInfs.dto.response.user.JwtAuthenticationResponse;
import com.example.ClothInfs.model.Role;
import com.example.ClothInfs.model.Usuario;
import com.example.ClothInfs.repository.UsuarioRepository;
import com.example.ClothInfs.service.user.AuthenticationService;
import com.example.ClothInfs.service.user.JwtService;

import lombok.Builder;

@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UsuarioRepository usuarioRepository; // Asegúrate de que UserRepository esté inyectado correctamente
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Constructor para inyección de dependencias (si usas Spring)
    public AuthenticationServiceImpl(UsuarioRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.usuarioRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Método para el registro de usuarios.
     * 
     * @param request la solicitud de registro
     * @return la respuesta de autenticación JWT
     */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        // Corrige la forma de construir el objeto 'User'
        Usuario user = new Usuario();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER); // Asegúrate de que Role.USER esté definido correctamente
        usuarioRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /**
     * Método para el inicio de sesión de usuarios.
     * 
     * @param request la solicitud de inicio de sesión
     * @return la respuesta de autenticación JWT
     */
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        // Maneja la autenticación
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
       // SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
