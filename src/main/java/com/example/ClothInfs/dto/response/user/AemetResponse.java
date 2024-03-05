package com.example.ClothInfs.dto.response.user;

/**
 * Clase que representa la respuesta de Aemet.
 */
public class AemetResponse {
    private String descripcion;
    private int estado;
    private String datos;
    private String metadatos;

    /**
     * Obtiene la descripción de la respuesta.
     *
     * @return La descripción de la respuesta
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la respuesta.
     *
     * @param descripcion La descripción de la respuesta
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado de la respuesta.
     *
     * @return El estado de la respuesta
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la respuesta.
     *
     * @param estado El estado de la respuesta
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Obtiene los datos de la respuesta.
     *
     * @return Los datos de la respuesta
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Establece los datos de la respuesta.
     *
     * @param datos Los datos de la respuesta
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    /**
     * Obtiene los metadatos de la respuesta.
     *
     * @return Los metadatos de la respuesta
     */
    public String getMetadatos() {
        return metadatos;
    }

    /**
     * Establece los metadatos de la respuesta.
     *
     * @param metadatos Los metadatos de la respuesta
     */
    public void setMetadatos(String metadatos) {
        this.metadatos = metadatos;
    }
}
