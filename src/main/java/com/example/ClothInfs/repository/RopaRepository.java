package com.example.ClothInfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ClothInfs.model.Ropa;

@Repository
public interface RopaRepository extends JpaRepository<Ropa, Long> {
}