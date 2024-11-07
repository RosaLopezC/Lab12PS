package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {

    @Autowired
    private VetService vetService;

    @GetMapping
    public List<Vet> getAllVets() {
        return vetService.findAll();
    }

    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetService.save(vet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vet> updateVet(@PathVariable Long id, @RequestBody Vet vetDetails) {
        Vet updatedVet = vetService.update(id, vetDetails);
        return ResponseEntity.ok(updatedVet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVet(@PathVariable Long id) {
        vetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
