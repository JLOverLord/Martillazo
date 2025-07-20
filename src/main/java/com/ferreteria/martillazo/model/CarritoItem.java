package com.ferreteria.martillazo.model;

public class CarritoItem {

    private Long productoId;
    private String nombre;
    private String imagen;
    private double precio;
    private int cantidad;

    // Constructor vacío
    public CarritoItem() {}

    // Constructor completo
    public CarritoItem(Long productoId, String nombre, String imagen, double precio, int cantidad) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
