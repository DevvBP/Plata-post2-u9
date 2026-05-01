package com.laboratorio.productos.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, BigDecimal precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public static ProductoBuilder builder() {
        return new ProductoBuilder();
    }

    public static class ProductoBuilder {
        private Long id;
        private String nombre;
        private String descripcion;
        private BigDecimal precio;
        private Integer stock;

        public ProductoBuilder id(Long id) { this.id = id; return this; }
        public ProductoBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public ProductoBuilder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public ProductoBuilder precio(BigDecimal precio) { this.precio = precio; return this; }
        public ProductoBuilder stock(Integer stock) { this.stock = stock; return this; }
        public Producto build() { return new Producto(id, nombre, descripcion, precio, stock); }
    }
}
