package com.biblioteca.libros;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private static final String JSON = """
            {"title":"Cien años de soledad","author":"Gabriel García Márquez",
             "isbn":"978-0307474728","publicationYear":1967,"category":"Novela","available":true}
            """;

    @Autowired
    MockMvc mvc;

    @Test
    void crudCompleto() throws Exception {
        mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
        mvc.perform(get("/api/books")).andExpect(status().isOk());
        mvc.perform(get("/api/books/1")).andExpect(status().isOk());
        mvc.perform(put("/api/books/1").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isOk());
        mvc.perform(delete("/api/books/1")).andExpect(status().isNoContent());
        mvc.perform(get("/api/books/1")).andExpect(status().isNotFound());
    }

    @Test
    void noExisteDevuelve404() throws Exception {
        mvc.perform(get("/api/books/999")).andExpect(status().isNotFound());
        mvc.perform(put("/api/books/999").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isNotFound());
        mvc.perform(delete("/api/books/999")).andExpect(status().isNotFound());
    }

    @Test
    void datosInvalidosDevuelve400() throws Exception {
        mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
    }
}
