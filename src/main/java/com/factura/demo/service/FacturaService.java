package com.factura.demo.service;

import com.factura.demo.dto.RequestFacturaDTO;
import com.factura.demo.dto.ResponseFacturaDTO;

import java.util.List;
import java.util.Optional;

public interface FacturaService {
    List<ResponseFacturaDTO> getAllFacturas();
    Optional<ResponseFacturaDTO> getFacturaById(Long id);
    void deleteFacturaById(Long id);
    ResponseFacturaDTO saveFactura(RequestFacturaDTO requestFacturaDTO);

}
