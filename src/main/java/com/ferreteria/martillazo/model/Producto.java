package com.ferreteria.martillazo.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 800, message = "La descripción debe tener entre 10 y 800 caracteres")
    private String descripcion;

    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
    private double precio;

    @Size(max = 255, message = "La URL de la imagen no debe superar los 255 caracteres")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "Debe seleccionar una categoría")
    private Categoria categoria;

    @Column(name = "destacado")
    private boolean destacado;

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
