package com.example.inventario.service;

import com.example.inventario.model.Producto;
import com.example.inventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

/*Para realizar el mockito */
    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private NotificacionService notificacionService;

    private InventarioService inventarioService;

    @BeforeEach
    void configurar() {

        inventarioService = new InventarioService(
                productoRepository,
                notificacionService
        );
    }

    @Test
    void debeReducirStockCorrectamente() {

        // Arrange
        Producto producto = new Producto(
                1L,
                "Laptop",
                10,
                5
        );

        when(productoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(producto));

        when(productoRepository.guardar(producto))
                .thenReturn(producto);

        // Act
        Producto resultado =
                inventarioService.reducirStock(1L, 3);

        // Assert
        assertEquals(7, resultado.getStock());

        verify(productoRepository).guardar(producto);
    }

    @Test
    void debeEnviarAlertaCuandoStockQuedaDebajoDelMinimo() {

        // Arrange
        Producto producto = new Producto(
                2L,
                "Teclado",
                6,
                5
        );

        when(productoRepository.buscarPorId(2L))
                .thenReturn(Optional.of(producto));

        when(productoRepository.guardar(producto))
                .thenReturn(producto);

        // Act
        inventarioService.reducirStock(2L, 2);

        // Assert
        assertEquals(4, producto.getStock());

        verify(notificacionService)
                .enviarAlerta(producto);
    }

    @Test
    void noDebeEnviarAlertaCuandoStockEsSuficiente() {

        // Arrange
        Producto producto = new Producto(
                3L,
                "Mouse",
                10,
                5
        );

        when(productoRepository.buscarPorId(3L))
                .thenReturn(Optional.of(producto));

        when(productoRepository.guardar(producto))
                .thenReturn(producto);

        // Act
        inventarioService.reducirStock(3L, 2);

        // Assert
        assertEquals(8, producto.getStock());

        verify(notificacionService, never())
                .enviarAlerta(producto);
    }

    @Test
    void noDebePermitirStockNegativo() {

        // Arrange
        Producto producto = new Producto(
                4L,
                "Monitor",
                5,
                2
        );

        when(productoRepository.buscarPorId(4L))
                .thenReturn(Optional.of(producto));

        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> inventarioService.reducirStock(4L, 6)
        );
    }

    @Test
    void debeAumentarStockCorrectamente() {

        // Arrange
        Producto producto = new Producto(
                5L,
                "Impresora",
                5,
                2
        );

        when(productoRepository.buscarPorId(5L))
                .thenReturn(Optional.of(producto));

        when(productoRepository.guardar(producto))
                .thenReturn(producto);

        // Act
        Producto resultado =
                inventarioService.aumentarStock(5L, 4);

        // Assert
        assertEquals(9, resultado.getStock());

        verify(productoRepository).guardar(producto);
    }
}