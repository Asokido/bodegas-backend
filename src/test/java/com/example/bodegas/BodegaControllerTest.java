package com.example.bodegas;

import com.example.bodegas.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BodegaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void productosDePruebaTienenBodega() {
        assertThat(productoRepository.findAll())
                .hasSize(3)
                .allSatisfy(p -> assertThat(p.getBodega()).isNotNull());
    }

    @Test
    void listarBodegasConProductos() throws Exception {
        mockMvc.perform(get("/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre").value("Bodega Norte"))
                .andExpect(jsonPath("$[0].productos", hasSize(2)))
                .andExpect(jsonPath("$[0].productos[0].nombre").value("Computador portátil"))
                .andExpect(jsonPath("$[1].nombre").value("Bodega Sur"))
                .andExpect(jsonPath("$[1].productos[0].nombre").value("Impresora"));
    }

    @Test
    void registrarBodega() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Bodega Centro\",\"ubicacion\":\"Zona centro\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Bodega Centro"));

        mockMvc.perform(get("/bodegas"))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void registrarBodegaSinDatos() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\",\"ubicacion\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
