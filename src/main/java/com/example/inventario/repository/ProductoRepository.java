package com.example.inventario.repository;

import java.util.List;
import java.util.Optional;

import com.example.inventario.model.Producto;

/**
 * Define las operaciones de almacenamiento de productos.
 * Esta interfaz permite separar la lógica de negocio
 * de la forma en que los productos son almacenados.
 */
public interface ProductoRepository {

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Long id);

    List<Producto> listarTodos();
}