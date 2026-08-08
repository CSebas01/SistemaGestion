package com.example.inventario.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ProductoTest {

    @Test
    void debeNecesitarAlertaCuandoStockEsMenorAlMinimo() {

        // Arrange: creamos un producto con stock por debajo del mínimo.
        Producto producto = new Producto(
        1L,
        "Laptop",
        3,
        5
);

        // Act: verificamos si necesita alerta.
        boolean resultado = producto.necesitaAlerta();

        // Assert: esperamos que la alerta sea necesaria.
        assertTrue(resultado);
    }

    @Test
    void noDebeNecesitarAlertaCuandoStockEsSuficiente() {

        // Arrange
        Producto producto = new Producto(
                2L,
                "Mouse",
                10,
                5
        );

        // Act
        boolean resultado = producto.necesitaAlerta();

        // Assert
        assertFalse(resultado);
    }
}