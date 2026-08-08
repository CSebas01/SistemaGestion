package com.example.inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventario.model.Producto;
import com.example.inventario.service.InventarioService;

/**
 * Controlador REST del sistema de inventario.
 * Se encarga únicamente de recibir peticiones HTTP
 * y delegar la lógica al InventarioService.
 */
@RestController
@RequestMapping("/productos")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Registra un nuevo producto.
     * POST /productos
     */
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(
            @RequestBody Producto producto) {

        Producto productoCreado =
                inventarioService.agregarProducto(producto);

        return ResponseEntity.ok(productoCreado);
    }

    /**
     * Obtiene todos los productos.
     * GET /productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {

        return ResponseEntity.ok(
                inventarioService.listarProductos()
        );
    }

    /**
     * Aumenta el stock de un producto.
     * PUT /productos/{id}/entrada?cantidad=10
     */
    @PutMapping("/{id}/entrada")
    public ResponseEntity<Producto> aumentarStock(
            @PathVariable Long id,
            @RequestParam int cantidad) {

        Producto producto =
                inventarioService.aumentarStock(id, cantidad);

        return ResponseEntity.ok(producto);
    }

    /**
     * Reduce el stock de un producto.
     * PUT /productos/{id}/salida?cantidad=5
     */
    @PutMapping("/{id}/salida")
    public ResponseEntity<Producto> reducirStock(
            @PathVariable Long id,
            @RequestParam int cantidad) {

        Producto producto =
                inventarioService.reducirStock(id, cantidad);

        return ResponseEntity.ok(producto);
    }
}