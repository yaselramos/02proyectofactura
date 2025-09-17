package com.factura.demo.dto;


import lombok.Data;

import java.util.Set;
@Data
public class RequestFacturaDTO {

    private Long idFactura;
    private String numeroFactura;
    private Set<RequestDetalleFacturaDTO> detalles;

    public RequestFacturaDTO() {
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Set<RequestDetalleFacturaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<RequestDetalleFacturaDTO> detalles) {
        this.detalles = detalles;
    }
}
