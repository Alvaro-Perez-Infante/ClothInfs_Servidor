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

@RestController
@RequestMapping("/api/ropa")
public class RopaController {

	@Autowired
	private RopaService ropaService;

	@GetMapping
	public ResponseEntity<List<Ropa>> getAllRopa() {
		List<Ropa> ropas = ropaService.getAllRopa();
		return new ResponseEntity<>(ropas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRopaById(@PathVariable("id") Long id) {
	    try {
	        Ropa ropa = ropaService.getRopaById(id);
	        return new ResponseEntity<>(ropa, HttpStatus.OK);
	    } catch (ResourceNotFoundException ex) {
	        return new ResponseEntity<>("No se encontró ninguna prenda de ropa con el ID proporcionado", HttpStatus.NOT_FOUND);
	    }
	}


	@PostMapping
	public ResponseEntity<Ropa> addRopa(@RequestBody Ropa ropa) {
		Ropa newRopa = ropaService.addRopa(ropa);
		return new ResponseEntity<>(newRopa, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ropa> updateRopa(@PathVariable("id") Long id, @RequestBody Ropa ropa) {
		Ropa updatedRopa = ropaService.updateRopa(id, ropa);
		if (updatedRopa != null) {
			return new ResponseEntity<>(updatedRopa, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRopa(@PathVariable("id") Long id) {
		ropaService.deleteRopa(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
