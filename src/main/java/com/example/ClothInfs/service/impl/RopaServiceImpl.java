package com.example.ClothInfs.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ClothInfs.exception.ResourceNotFoundException;
import com.example.ClothInfs.model.Ropa;
import com.example.ClothInfs.repository.RopaRepository;
import com.example.ClothInfs.service.RopaService;

/**
 * Implementación del servicio de ropa.
 */
@Service
public class RopaServiceImpl implements RopaService {

    @Autowired
    private RopaRepository ropaRepository;

    @Override
    public List<Ropa> getAllRopa() {
        return ropaRepository.findAll();
    }

    @Override
    public Ropa getRopaById(Long id) {
        return ropaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró ninguna prenda de ropa con el ID: " + id));
    }

    @Override
    public Ropa addRopa(Ropa clothInfs) {
        // Verificar si algún campo de texto está vacío o nulo
        if (clothInfs.getNombre() == null || clothInfs.getTipo() == null || clothInfs.getSize() == null) {
            throw new IllegalArgumentException("No se pueden agregar ropas con campos de texto vacíos o nulos");
        }

        // Guardar la ropa en el repositorio
        return ropaRepository.save(clothInfs);
    }

    @Override
    public Ropa updateRopa(Long id, Ropa ropa) {
        // Verificar si la ropa existente está presente en el repositorio
        Ropa existingRopa = ropaRepository.findById(id).orElse(null);
        if (existingRopa == null) {
            throw new ResourceNotFoundException("No se encontró ninguna prenda de ropa con el ID proporcionado");
        }

        // Verificar si algún campo de texto de la nueva ropa está vacío o nulo
        if (ropa.getNombre() == null || ropa.getTipo() == null || ropa.getSize() == null) {
            throw new IllegalArgumentException("No se pueden actualizar ropas con campos de texto vacíos o nulos");
        }

        // Actualizar los campos de la ropa existente con los valores de la nueva ropa
        existingRopa.setNombre(ropa.getNombre());
        existingRopa.setTipo(ropa.getTipo());
        existingRopa.setSize(ropa.getSize());

        // Verificar si los campos numéricos de la nueva ropa son null antes de actualizar
        if (Objects.nonNull(ropa.getPrecio())) {
            existingRopa.setPrecio(ropa.getPrecio());
        }
        if (Objects.nonNull(ropa.getCantidad())) {
            existingRopa.setCantidad(ropa.getCantidad());
        }

        // Guardar la ropa actualizada en el repositorio
        return ropaRepository.save(existingRopa);
    }

    @Override
    public void deleteRopa(Long id) {
        try {
            ropaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("No se encontró ninguna prenda de ropa con el ID proporcionado");
        }
    }
}
