package com.factura.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String detalle;
}
