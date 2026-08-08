package com.example.inventario.model;

public class Producto {

    private Long id;
    private String nombre;
    private int stock;
    private int stockMinimo;

    public Producto() {
    }

    public Producto(Long id, String nombre, int stock, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public boolean necesitaAlerta() {
        return stock < stockMinimo;
    }
}