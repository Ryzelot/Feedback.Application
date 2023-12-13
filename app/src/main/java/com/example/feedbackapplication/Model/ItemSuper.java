package com.example.feedbackapplication.Model;

public class ItemSuper {

    // Atributos = Capos de la tabla
    private Integer id;
    private String producto;
    private String marca;
    private String cantidad;

    // Constructor
    public ItemSuper() {
    }

    public ItemSuper(Integer id, String producto, String marca, String cantidad) {
        this.id = id;
        this.producto = producto;
        this.marca = marca;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
