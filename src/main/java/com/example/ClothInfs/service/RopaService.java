package com.example.ClothInfs.service;

import java.util.List;

import com.example.ClothInfs.model.Ropa;

public interface RopaService {

    List<Ropa> getAllRopa();

    Ropa getRopaById(Long id);

    Ropa addRopa(Ropa clothInfs);

    Ropa updateRopa(Long id, Ropa ropa);

    void deleteRopa(Long id);

}
