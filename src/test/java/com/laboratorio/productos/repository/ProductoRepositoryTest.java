package com.laboratorio.productos.repository;

import com.laboratorio.productos.domain.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void save_asignaIdAutomaticamente() {
        Producto producto = Producto.builder()
                .nombre("Laptop")
                .descripcion("Laptop de 16 pulgadas")
                .precio(new BigDecimal("1500.00"))
                .stock(10)
                .build();

        Producto guardado = productoRepository.save(producto);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getId()).isNotNull();
        assertThat(guardado.getId()).isGreaterThan(0);
        assertThat(guardado.getNombre()).isEqualTo("Laptop");
    }

    @Test
    void findById_existente_retornaProducto() {
        Producto producto = Producto.builder()
                .nombre("Mouse")
                .descripcion("Mouse inalambrico")
                .precio(new BigDecimal("25.50"))
                .stock(50)
                .build();
        Producto guardado = productoRepository.save(producto);

        Optional<Producto> encontrado = productoRepository.findById(guardado.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Mouse");
    }

    @Test
    void findAll_retornaListaCompleta() {
        Producto p1 = Producto.builder().nombre("P1").precio(BigDecimal.TEN).stock(1).build();
        Producto p2 = Producto.builder().nombre("P2").precio(BigDecimal.TEN).stock(1).build();
        productoRepository.saveAll(List.of(p1, p2));

        List<Producto> productos = productoRepository.findAll();

        assertThat(productos).hasSize(2);
    }

    @Test
    void deleteById_eliminaProducto() {
        Producto producto = Producto.builder().nombre("Teclado").precio(new BigDecimal("45.00")).stock(20).build();
        Producto guardado = productoRepository.save(producto);

        productoRepository.deleteById(guardado.getId());
        Optional<Producto> eliminado = productoRepository.findById(guardado.getId());

        assertThat(eliminado).isEmpty();
    }
}
