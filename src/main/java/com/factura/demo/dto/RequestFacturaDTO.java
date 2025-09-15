package com.factura.demo.dto;

import lombok.Data;

import java.util.Set;
@Data
public class RequestFacturaDTO {

    private Long idFactura;
    private String numeroFactura;
    private Set<RequestDetalleFacturaDTO> detalles;
}
