package com.example.ClothInfs.conf;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.example.ClothInfs.model.Role;
import com.example.ClothInfs.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    UsuarioService usuarioService;

    /**
     * Configuración del filtro de seguridad y las reglas de autorización.
     *
     * @param http La configuración de HttpSecurity
     * @return El SecurityFilterChain configurado
     * @throws Exception Si ocurre un error en la configuración de seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ropa/**").hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.POST, "/api/ropa/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.PUT, "/api/ropa/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .requestMatchers(HttpMethod.DELETE, "/api/ropa/**").hasAuthority(Role.ROLE_ADMIN.toString())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configuración del encriptador de contraseñas BCrypt.
     *
     * @return El encriptador de contraseñas BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración del proveedor de autenticación DAO.
     *
     * @return El proveedor de autenticación DAO configurado
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService.usuarioDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configuración del administrador de autenticación.
     *
     * @param config La configuración de autenticación
     * @return El administrador de autenticación configurado
     * @throws Exception Si ocurre un error al obtener el administrador de autenticación
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configuración de RestTemplate.
     *
     * @return RestTemplate configurado
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
