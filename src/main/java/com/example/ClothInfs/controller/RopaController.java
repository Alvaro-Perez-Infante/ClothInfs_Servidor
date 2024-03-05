package com.example.ClothInfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ClothInfs.exception.ResourceNotFoundException;
import com.example.ClothInfs.model.Ropa;
import com.example.ClothInfs.service.RopaService;

/**
 * Controlador para la gestión de ropa.
 */
@RestController
@RequestMapping("/api/ropa")
public class RopaController {

    @Autowired
    private RopaService ropaService;

    /**
     * Obtiene todas las prendas de ropa.
     *
     * @return ResponseEntity con la lista de prendas de ropa y el estado HTTP OK
     */
    @GetMapping
    public ResponseEntity<List<Ropa>> getAllRopa() {
        List<Ropa> ropas = ropaService.getAllRopa();
        return new ResponseEntity<>(ropas, HttpStatus.OK);
    }

    /**
     * Obtiene una prenda de ropa por su ID.
     *
     * @param id El ID de la prenda de ropa
     * @return ResponseEntity con la prenda de ropa encontrada o un mensaje de error y el estado HTTP correspondiente
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getRopaById(@PathVariable("id") Long id) {
        try {
            Ropa ropa = ropaService.getRopaById(id);
            return new ResponseEntity<>(ropa, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>("No se encontró ninguna prenda de ropa con el ID proporcionado", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Agrega una nueva prenda de ropa.
     *
     * @param ropa La nueva prenda de ropa a agregar
     * @return ResponseEntity con la prenda de ropa agregada y el estado HTTP CREATED
     */
    @PostMapping
    public ResponseEntity<Ropa> addRopa(@RequestBody Ropa ropa) {
        Ropa newRopa = ropaService.addRopa(ropa);
        return new ResponseEntity<>(newRopa, HttpStatus.CREATED);
    }

    /**
     * Actualiza una prenda de ropa existente.
     *
     * @param id   El ID de la prenda de ropa a actualizar
     * @param ropa La prenda de ropa actualizada
     * @return ResponseEntity con la prenda de ropa actualizada y el estado HTTP correspondiente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ropa> updateRopa(@PathVariable("id") Long id, @RequestBody Ropa ropa) {
        Ropa updatedRopa = ropaService.updateRopa(id, ropa);
        if (updatedRopa != null) {
            return new ResponseEntity<>(updatedRopa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una prenda de ropa por su ID.
     *
     * @param id El ID de la prenda de ropa a eliminar
     * @return ResponseEntity con el estado HTTP NO_CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRopa(@PathVariable("id") Long id) {
        ropaService.deleteRopa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
