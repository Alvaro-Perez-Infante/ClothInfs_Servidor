package com.example.ClothInfs.dto.response.user;

/**
 * Clase que representa la respuesta de autenticación JWT.
 */
public class JwtAuthenticationResponse {
    private String token;

    /**
     * Constructor de la clase.
     *
     * @param token El token de autenticación JWT
     */
    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    /**
     * Obtiene el token de autenticación JWT.
     *
     * @return El token de autenticación JWT
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token de autenticación JWT.
     *
     * @param token El token de autenticación JWT
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Método estático para obtener un constructor de objetos JwtAuthenticationResponse.
     *
     * @return Un constructor de objetos JwtAuthenticationResponse
     */
    public static JwtAuthenticationResponseBuilder builder() {
        return new JwtAuthenticationResponseBuilder();
    }

    /**
     * Clase interna para construir objetos JwtAuthenticationResponse de manera fluída.
     */
    public static class JwtAuthenticationResponseBuilder {
        private String token;

        /**
         * Establece el token en el constructor fluído.
         *
         * @param token El token de autenticación JWT
         * @return El constructor fluído de JwtAuthenticationResponse
         */
        public JwtAuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * Construye un objeto JwtAuthenticationResponse.
         *
         * @return Un objeto JwtAuthenticationResponse
         */
        public JwtAuthenticationResponse build() {
            return new JwtAuthenticationResponse(token);
        }
    }
}
