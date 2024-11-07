package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import org.springframework.stereotype.Component;

@Component
public class VetMapper {

    public VetTO toVetTO(Vet vet) {
        return new VetTO(
                vet.getId(),
                vet.getNombre(),
                vet.getApellido(),
                vet.getNumeroLicencia(),
                vet.getEspecialidad()
        );
    }

    public Vet toVet(VetTO vetTO) {
        Vet vet = new Vet();
        vet.setId(vetTO.getId());
        vet.setNombre(vetTO.getNombre());
        vet.setApellido(vetTO.getApellido());
        vet.setNumeroLicencia(vetTO.getNumeroLicencia());
        vet.setEspecialidad(vetTO.getEspecialidad());
        return vet;
    }
}
