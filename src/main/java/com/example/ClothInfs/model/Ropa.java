package com.example.ClothInfs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Clase que representa una prenda de ropa en el sistema.
 */
@Entity
public class Ropa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String tipo;
    private String size;
    private double precio;
    private int cantidad;

    /**
     * Constructor por defecto de la clase Ropa.
     */
    public Ropa() {
        super();
    }

    /**
     * Constructor de la clase Ropa con parámetros.
     *
     * @param id       El ID de la prenda de ropa.
     * @param nombre   El nombre de la prenda de ropa.
     * @param tipo     El tipo de la prenda de ropa.
     * @param size     El tamaño de la prenda de ropa.
     * @param precio   El precio de la prenda de ropa.
     * @param cantidad La cantidad de la prenda de ropa disponible.
     */
    public Ropa(Long id, String nombre, String tipo, String size, double precio, int cantidad) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.size = size;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
