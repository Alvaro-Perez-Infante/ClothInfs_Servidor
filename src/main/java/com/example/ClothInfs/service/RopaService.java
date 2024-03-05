package com.example.ClothInfs.service;

import java.util.List;

import com.example.ClothInfs.model.Ropa;

/**
 * Interfaz que define los métodos para el servicio relacionado con la entidad Ropa.
 */
public interface RopaService {

    /**
     * Obtiene todas las ropas.
     *
     * @return Una lista de todas las ropas.
     */
    List<Ropa> getAllRopa();

    /**
     * Obtiene una prenda de ropa por su identificador.
     *
     * @param id El identificador de la prenda de ropa.
     * @return La prenda de ropa encontrada.
     */
    Ropa getRopaById(Long id);

    /**
     * Agrega una nueva prenda de ropa.
     *
     * @param ropa La prenda de ropa a agregar.
     * @return La prenda de ropa agregada.
     */
    Ropa addRopa(Ropa ropa);

    /**
     * Actualiza una prenda de ropa existente.
     *
     * @param id   El identificador de la prenda de ropa a actualizar.
     * @param ropa La prenda de ropa actualizada.
     * @return La prenda de ropa actualizada.
     */
    Ropa updateRopa(Long id, Ropa ropa);

    /**
     * Elimina una prenda de ropa por su identificador.
     *
     * @param id El identificador de la prenda de ropa a eliminar.
     */
    void deleteRopa(Long id);
}
