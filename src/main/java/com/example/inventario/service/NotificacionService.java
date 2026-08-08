package com.example.inventario.service;

import com.example.inventario.model.Producto;

/**
 * Define el comportamiento para enviar alertas
 * cuando un producto tiene poco stock.
 * Al ser una interfaz, el sistema no depende
 * de una implementación concreta.
 */
public interface NotificacionService {

    void enviarAlerta(Producto producto);
}