package com.laboratorio.productos.service;

import com.laboratorio.productos.domain.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    Producto buscarPorId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);
}
