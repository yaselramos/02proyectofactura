package com.factura.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "facturas")


public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroFactura;
    private BigDecimal subtotal;
    private BigDecimal total;
    @Transient
    private final BigDecimal IVA = BigDecimal.valueOf(0.15);
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "factura", orphanRemoval = true)
    private Set<DetalleFactura> detalles;
    public Factura() {
    }

    public Factura(Long id, String numeroFactura, BigDecimal subtotal, BigDecimal total, LocalDateTime fechaCreacion, Set<DetalleFactura> detalles) {
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

    public BigDecimal getIVA() {
        return IVA;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
