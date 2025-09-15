package com.factura.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_factura")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProducto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal total;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JsonIgnore
    private Factura factura;
}
