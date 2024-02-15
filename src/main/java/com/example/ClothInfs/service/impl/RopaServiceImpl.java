package com.example.ClothInfs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ClothInfs.model.Ropa;
import com.example.ClothInfs.repository.RopaRepository;
import com.example.ClothInfs.service.RopaService;

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
        return ropaRepository.findById(id).orElse(null);
    }

    @Override
    public Ropa addRopa(Ropa clothInfs) {
        return ropaRepository.save(clothInfs);
    }

    @Override
    public Ropa updateRopa(Long id, Ropa ropa) {
        Ropa existingRopa = ropaRepository.findById(id).orElse(null);
        if (existingRopa != null) {
            existingRopa.setNombre(ropa.getNombre());
            existingRopa.setTipo(ropa.getTipo());
            existingRopa.setSize(ropa.getSize());
            existingRopa.setPrecio(ropa.getPrecio());
            existingRopa.setCantidad(ropa.getCantidad());
        }
        return ropaRepository.save(existingRopa);
    }

    @Override
    public void deleteRopa(Long id) {
        ropaRepository.deleteById(id);
    }
}
