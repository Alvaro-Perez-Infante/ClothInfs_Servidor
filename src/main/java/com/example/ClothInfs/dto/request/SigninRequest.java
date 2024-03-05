package com.example.ClothInfs.dto.request;

/**
 * Clase que representa la solicitud de inicio de sesión.
 */
public class SigninRequest {
    private String email;
    private String password;

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
