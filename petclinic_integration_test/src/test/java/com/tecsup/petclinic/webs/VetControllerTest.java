package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static javax.management.Query.eq;
import static javax.swing.UIManager.put;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VetRepository vetRepository;

    @Test
    public void testCreateVet() throws Exception {
        String vetJson = "{\"nombre\":\"John\",\"apellido\":\"Doe\",\"numeroLicencia\":\"12345\",\"especialidad\":\"Cardiología\"}";

        mockMvc.perform(post("/api/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vetJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("John")))
                .andExpect(jsonPath("$.apellido", is("Doe")))
                .andExpect(jsonPath("$.numeroLicencia", is("12345")))
                .andExpect(jsonPath("$.especialidad", is("Cardiología")));

        System.out.println("Prueba de integración testCreateVet realizada exitosamente.");
    }

    @Test
    public void testDeleteVet() throws Exception {
        Vet vet = new Vet();
        vet.setNombre("John");
        vet.setApellido("Doe");
        vet.setNumeroLicencia("12345");
        vet.setEspecialidad("Cardiología");

        Vet savedVet = vetRepository.save(vet);

        mockMvc.perform(delete("/api/vets/{id}", savedVet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Optional<Vet> deletedVet = vetRepository.findById(savedVet.getId());
        assertFalse(deletedVet.isPresent(), "El Vet debería haber sido eliminado");
    }

    @Test
    public void testUpdateVet() throws Exception {
        // Crear un veterinario existente
        Vet vet = new Vet();
        vet.setNombre("John");
        vet.setApellido("Doe");
        vet.setNumeroLicencia("12345");
        vet.setEspecialidad("Cardiología");

        // Guardar el veterinario en la base de datos
        Vet savedVet = vetRepository.save(vet);

        // JSON con la información actualizada
        String updatedVetJson = "{\"nombre\":\"Jane\",\"apellido\":\"Smith\",\"numeroLicencia\":\"67890\",\"especialidad\":\"Dermatología\"}";

        // Realizar la solicitud de actualización
        mockMvc.perform(MockMvcRequestBuilders.put("/api/vets/{id}", savedVet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedVetJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Jane")))
                .andExpect(jsonPath("$.apellido", is("Smith")))
                .andExpect(jsonPath("$.numeroLicencia", is("67890")))
                .andExpect(jsonPath("$.especialidad", is("Dermatología")));
    }
}
