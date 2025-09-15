package com.factura.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ResponseDetalleFacturaDTO {
    private Long IdProducto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal total;
}
