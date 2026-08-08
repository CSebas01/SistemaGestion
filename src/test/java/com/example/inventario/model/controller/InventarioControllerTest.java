package com.example.inventario.controller;

import com.example.inventario.model.Producto;
import com.example.inventario.repository.ProductoRepository;
import com.example.inventario.service.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoRepository productoRepository;

    @MockBean
    private NotificacionService notificacionService;

    @BeforeEach
    void configurarMocks() {

        when(productoRepository.guardar(any(Producto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void debeRegistrarProducto() throws Exception {

        String json = """
                {
                    "id": 1,
                    "nombre": "Laptop",
                    "stock": 10,
                    "stockMinimo": 5
                }
                """;

        mockMvc.perform(
                post("/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre")
                .value("Laptop"))
        .andExpect(jsonPath("$.stock")
                .value(10));
    }

    @Test
    void debeResponderListaDeProductos() throws Exception {

        when(productoRepository.listarTodos())
                .thenReturn(
     java.util.List.of(
   new Producto(
   1L,
   "Laptop",
   10,
   5
  )
         )
                );

        mockMvc.perform(
                get("/productos")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nombre")
                .value("Laptop"));
    }
}