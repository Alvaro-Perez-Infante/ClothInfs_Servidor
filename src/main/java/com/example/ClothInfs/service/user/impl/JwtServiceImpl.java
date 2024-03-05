package com.example.ClothInfs.service.user.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ClothInfs.service.user.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
    /**
     * Llave para firmar el JWT.
     */
    @Value("${jwt.secret}")
    private String jwtSigningKey;

    /**
     * Extrae el nombre de usuario (subject) del token JWT.
     *
     * @param token el token JWT del que se extraerá el nombre de usuario
     * @return el nombre de usuario extraído del token JWT
     */
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Genera un token JWT para un usuario con detalles específicos (UserDetails).
     *
     * @param userDetails los detalles del usuario para los que se generará el token JWT
     * @return el token JWT generado
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Verifica si un token JWT es válido comparando el nombre de usuario y comprobando si ha expirado.
     *
     * @param token el token JWT a verificar
     * @param userDetails los detalles del usuario con los que se comparará el nombre de usuario
     * @return true si el token es válido, false de lo contrario
     */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Método genérico para extraer información del token JWT.
     *
     * @param token el token JWT del que se extraerá la información
     * @param claimsResolvers la función de resolución de claims
     * @param <T> el tipo de la información a extraer
     * @return la información extraída del token JWT
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Genera un token JWT incluyendo los claims adicionales y los detalles del usuario.
     *
     * @param extraClaims los claims adicionales a incluir en el token JWT
     * @param userDetails los detalles del usuario para los que se generará el token JWT
     * @return el token JWT generado
     */
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token expira en 10 horas
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token el token JWT a verificar
     * @return true si el token ha expirado, false de lo contrario
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     *
     * @param token el token JWT del que se extraerá la fecha de expiración
     * @return la fecha de expiración extraída del token JWT
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae todos los claims del token JWT.
     *
     * @param token el token JWT del que se extraerán los claims
     * @return los claims extraídos del token JWT
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtiene la llave de firma para el token JWT.
     *
     * @return la llave de firma para el token JWT
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
