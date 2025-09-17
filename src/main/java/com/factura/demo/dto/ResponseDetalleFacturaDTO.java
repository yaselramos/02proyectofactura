package com.factura.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ResponseDetalleFacturaDTO {
    private Long IdProducto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal total;

    public ResponseDetalleFacturaDTO() {
    }

    public ResponseDetalleFacturaDTO(Long idProducto, Integer cantidad, BigDecimal precio, BigDecimal total) {
        IdProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Long idProducto) {
        IdProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
