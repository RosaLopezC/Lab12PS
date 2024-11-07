package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;

import java.util.List;

public interface VetService {
    List<Vet> findAll();
    Vet save(Vet vet);
    Vet update(Long id, Vet vetDetails);
    void delete(Long id);
}
