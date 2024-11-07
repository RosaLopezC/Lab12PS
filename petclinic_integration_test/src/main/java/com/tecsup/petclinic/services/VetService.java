package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import java.util.Optional;

public interface VetService {
    Vet save(Vet vet);
    Optional<Vet> findById(Long id);
    void delete(Long id);
}
