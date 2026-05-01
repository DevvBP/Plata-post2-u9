package com.laboratorio.productos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratorio.productos.domain.Producto;
import com.laboratorio.productos.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarProductos_retorna200ConLista() throws Exception {
        Producto producto = Producto.builder().id(1L).nombre("Teclado").precio(new BigDecimal("20.00")).stock(5).build();
        given(productoService.listarTodos()).willReturn(List.of(producto));

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Teclado"))
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void crearProducto_datosValidos_retorna201() throws Exception {
        Producto nuevoProducto = Producto.builder().nombre("Monitor").precio(new BigDecimal("150.00")).stock(10).build();
        Producto guardado = Producto.builder().id(1L).nombre("Monitor").precio(new BigDecimal("150.00")).stock(10).build();

        given(productoService.guardar(any(Producto.class))).willReturn(guardado);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Monitor"));
    }

    @Test
    void buscarProducto_noExistente_retorna404() throws Exception {
        given(productoService.buscarPorId(999L)).willThrow(new RuntimeException("No encontrado"));

        mockMvc.perform(get("/api/productos/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
