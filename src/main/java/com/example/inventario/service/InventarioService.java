package com.example.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.inventario.model.Producto;
import com.example.inventario.repository.ProductoRepository;

/**
 * Contiene la lógica de negocio relacionada con el inventario.
 * esta clase no se encarga de almacenar directamente los productos
 * ni de enviar las notificaciones.
 */
@Service
public class InventarioService {

    private final ProductoRepository productoRepository;
    private final NotificacionService notificacionService;

    /**
     * Inyección de dependencias mediante constructor.
     *
     * @param productoRepository repositorio de productos.
     * @param notificacionService servicio encargado de las alertas.
     */
    public InventarioService(
            ProductoRepository productoRepository,
            NotificacionService notificacionService) {

        this.productoRepository = productoRepository;
        this.notificacionService = notificacionService;
    }

    /**
     * Registra un producto en el inventario.
     */
    public Producto agregarProducto(Producto producto) {

        validarProducto(producto);

        return productoRepository.guardar(producto);
    }

    /**
     * Aumenta la cantidad de stock de un producto.
     */
    public Producto aumentarStock(Long id, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        Producto producto = obtenerProducto(id);

        producto.setStock(producto.getStock() + cantidad);

        return productoRepository.guardar(producto);
    }

    /**
     * Reduce la cantidad de stock de un producto.
     *
     * Si después de la reducción el stock queda por debajo
     * del mínimo permitido, se genera una alerta.
     */
    public Producto reducirStock(Long id, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        Producto producto = obtenerProducto(id);

        int nuevoStock = producto.getStock() - cantidad;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo."
            );
        }

        producto.setStock(nuevoStock);

        Producto productoActualizado =
                productoRepository.guardar(producto);

        if (productoActualizado.necesitaAlerta()) {
            notificacionService.enviarAlerta(productoActualizado);
        }

        return productoActualizado;
    }

    /**
     * Obtiene todos los productos registrados.
     */
    public List<Producto> listarProductos() {
        return productoRepository.listarTodos();
    }

    /**
     * Busca un producto por su identificador.
     */
    public Producto obtenerProducto(Long id) {

        return productoRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Producto no encontrado."
                        ));
    }

    /**
     * Valida los datos básicos del producto.
     */
    private void validarProducto(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo."
            );
        }

        if (producto.getId() == null) {
            throw new IllegalArgumentException(
                    "El producto debe tener un ID."
            );
        }

        if (producto.getNombre() == null ||
                producto.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El producto debe tener un nombre."
            );
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo."
            );
        }

        if (producto.getStockMinimo() < 0) {
            throw new IllegalArgumentException(
                    "El stock mínimo no puede ser negativo."
            );
        }
    }
}