package com.factura.demo.dto;

import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ResponseFacturaDTO {

    private Long id;
    private String numeroFactura;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private Set<ResponseDetalleFacturaDTO> detalles;
}
