package com.factura.demo.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ResponseFacturaDTO {

    private Long id;
    private String numeroFactura;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private Set<ResponseDetalleFacturaDTO> detalles;

    public ResponseFacturaDTO() {
    }

    public ResponseFacturaDTO(Long id, String numeroFactura, BigDecimal subtotal, BigDecimal total, LocalDateTime fechaCreacion, Set<ResponseDetalleFacturaDTO> detalles) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.subtotal = subtotal;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<ResponseDetalleFacturaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<ResponseDetalleFacturaDTO> detalles) {
        this.detalles = detalles;
    }
}
