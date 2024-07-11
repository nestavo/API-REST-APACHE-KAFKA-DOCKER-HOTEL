package es.nestavo.api.pruebas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.nestavo.api.modelo.Modelo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControlIntegrationTest {



    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCrearReserva() throws Exception {
        Modelo res = new Modelo(); // Crear un objeto Modelo para la prueba

        mockMvc.perform(post("/hotel/crearRes")
                .content(asJsonString(res))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Agrega más pruebas utilizando MockMvc según tus necesidades

    // Método para convertir un objeto a formato JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

