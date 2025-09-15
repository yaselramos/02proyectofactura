package com.factura.demo.controller;

import com.factura.demo.dto.RequestFacturaDTO;
import com.factura.demo.dto.ResponseFacturaDTO;
import com.factura.demo.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @PostMapping
    public ResponseEntity<ResponseFacturaDTO> createFactura(@RequestBody RequestFacturaDTO requestFacturaDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.saveFactura(requestFacturaDTO)) ;
    }

    @GetMapping
    public ResponseEntity<List<ResponseFacturaDTO>> getAllFacturas() {
        List<ResponseFacturaDTO> facturas = facturaService.getAllFacturas();
        if(facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturaService.getAllFacturas()) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFacturaDTO> getFacturaById(@PathVariable Long id) {
        return facturaService.getFacturaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacturaById(@PathVariable Long id) {
        facturaService.deleteFacturaById(id);
        return ResponseEntity.noContent().build();
    }
}
