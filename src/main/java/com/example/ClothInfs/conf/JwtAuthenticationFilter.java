package com.example.ClothInfs.conf;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.ClothInfs.service.UsuarioService;
import com.example.ClothInfs.service.user.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * El filtro JwtAuthenticationFilter intercepta las solicitudes para autenticar usuarios utilizando JWT.
 * Extiende OncePerRequestFilter para garantizar que se ejecute una vez por solicitud.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Este método se ejecuta para cada solicitud y realiza la autenticación del usuario utilizando JWT.
     *
     * @param request     El objeto HttpServletRequest
     * @param response    El objeto HttpServletResponse
     * @param filterChain El objeto FilterChain para pasar la solicitud al siguiente filtro
     * @throws ServletException Si ocurre un error en la ejecución del filtro
     * @throws IOException      Si ocurre un error de E/S durante la ejecución del filtro
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userNombre;
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userNombre = jwtService.extractUserName(jwt);
        if (StringUtils.isNotEmpty(userNombre)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usuarioService.usuarioDetailsService()
                    .loadUserByUsername(userNombre);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
