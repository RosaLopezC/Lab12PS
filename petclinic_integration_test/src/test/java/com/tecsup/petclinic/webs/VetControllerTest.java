package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")  // Activa el perfil H2 para esta prueba
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VetRepository vetRepository;

    @Test
    public void testCreateVet() throws Exception {
        // JSON con los datos del veterinario
        String vetJson = "{\"nombre\":\"John\",\"apellido\":\"Doe\",\"numeroLicencia\":\"12345\",\"especialidad\":\"Cardiología\"}";

        mockMvc.perform(post("/api/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vetJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("John")))
                .andExpect(jsonPath("$.apellido", is("Doe")))
                .andExpect(jsonPath("$.numeroLicencia", is("12345")))
                .andExpect(jsonPath("$.especialidad", is("Cardiología")));
    }
}
