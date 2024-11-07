package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServiceImpl implements VetService {

    @Autowired
    private VetRepository vetRepository;

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet update(Long id, Vet vetDetails) {
        Optional<Vet> optionalVet = vetRepository.findById(id);
        if (optionalVet.isPresent()) {
            Vet vet = optionalVet.get();
            vet.setNombre(vetDetails.getNombre());
            vet.setApellido(vetDetails.getApellido());
            vet.setNumeroLicencia(vetDetails.getNumeroLicencia());
            vet.setEspecialidad(vetDetails.getEspecialidad());
            return vetRepository.save(vet);
        } else {
            throw new RuntimeException("Vet not found");
        }
    }

    @Override
    public void delete(Long id) {
        vetRepository.deleteById(id);
    }
}
