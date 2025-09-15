package com.factura.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
}
