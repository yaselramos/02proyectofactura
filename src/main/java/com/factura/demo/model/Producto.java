package com.factura.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;
    private String detalle;
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @UpdateTimestamp
    private LocalDate fechaActualizacion;
}
