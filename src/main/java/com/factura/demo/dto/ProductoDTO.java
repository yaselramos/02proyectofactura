package com.factura.demo.dto;

import lombok.*;


@ToString
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String detalle;

    public ProductoDTO(Long id, String nombre, Double precio, String detalle) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.detalle = detalle;
    }

    public ProductoDTO() {

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
