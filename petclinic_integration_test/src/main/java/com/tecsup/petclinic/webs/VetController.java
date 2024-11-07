package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    @Autowired
    private VetRepository vetRepository;

    @PostMapping
    public ResponseEntity<Vet> createVet(@RequestBody Vet vet) {
        Vet savedVet = vetRepository.save(vet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVet);
    }
}
