package com.factura.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetalleFacturaDTO {
    private Long idProducto;
    private Integer cantidad;


}
