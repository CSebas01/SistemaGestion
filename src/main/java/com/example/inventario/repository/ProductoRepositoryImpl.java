package com.example.inventario.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.inventario.model.Producto;

/**
 * Implementación en memoria del repositorio de productos.
 * No utilizamos una base de datos para mantener el proyecto
 * sencillo y concentrarnos en SOLID, TDD y pruebas xd.
 */
@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private final Map<Long, Producto> productos = new HashMap<>();

    @Override
    public Producto guardar(Producto producto) {
        productos.put(producto.getId(), producto);
        return producto;
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos.values());
    }
}