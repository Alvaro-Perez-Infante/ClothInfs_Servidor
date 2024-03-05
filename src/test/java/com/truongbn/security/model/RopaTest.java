package com.truongbn.security.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ClothInfs.model.Ropa;

public class RopaTest {

    private Ropa ropa;

    @BeforeEach
    void setUp() {
        ropa = new Ropa();
    }

    @Test
    void testGetId() {
        Long id = 123L;
        ropa.setId(id);
        assertEquals(id, ropa.getId());
    }

    @Test
    void testGetNombre() {
        String nombre = "Camisa";
        ropa.setNombre(nombre);
        assertEquals(nombre, ropa.getNombre());
    }

    @Test
    void testGetTipo() {
        String tipo = "Camiseta";
        ropa.setTipo(tipo);
        assertEquals(tipo, ropa.getTipo());
    }

    @Test
    void testGetSize() {
        String size = "M";
        ropa.setSize(size);
        assertEquals(size, ropa.getSize());
    }

    @Test
    void testGetPrecio() {
        double precio = 29.99;
        ropa.setPrecio(precio);
        assertEquals(precio, ropa.getPrecio());
    }

    @Test
    void testGetCantidad() {
        int cantidad = 10;
        ropa.setCantidad(cantidad);
        assertEquals(cantidad, ropa.getCantidad());
    }
}
