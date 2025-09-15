package com.factura.demo.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class RequestDetalleFacturaDTO {
    private Long idProducto;
    private Integer cantidad;


}
