package com.example.inventario.service;

import org.springframework.stereotype.Service;

import com.example.inventario.model.Producto;

/**
 * Para este proyecto solamente mostramos la alerta
 * en consola, no necesitamos configurar un correo real.
 */
@Service
public class EmailNotificacionService implements NotificacionService {

    @Override
    public void enviarAlerta(Producto producto) {

        System.out.println(
                "ALERTA: El producto " +
                producto.getNombre() +
                " tiene poco inventario. Stock actual: " +
                producto.getStock() +
                ", stock mínimo: " +
                producto.getStockMinimo()
        );
    }
}