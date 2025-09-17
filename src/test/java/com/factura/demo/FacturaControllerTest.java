package com.factura.demo;

import com.factura.demo.controller.FacturaController;
import com.factura.demo.dto.RequestFacturaDTO;
import com.factura.demo.dto.ResponseFacturaDTO;
import com.factura.demo.service.FacturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaControllerTest {
    @Mock
    FacturaService facturaService;
    @InjectMocks
    FacturaController facturaController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void CreateFacturaTest() {
        RequestFacturaDTO requestFacturaDTO=new RequestFacturaDTO();
        ResponseFacturaDTO responseFacturaDTO=new ResponseFacturaDTO();
        Mockito.when(facturaService.saveFactura(requestFacturaDTO)).thenReturn(responseFacturaDTO);
        ResponseEntity<ResponseFacturaDTO> response=facturaController.createFactura(requestFacturaDTO);
        assertEquals(201,response.getStatusCodeValue());
        assertEquals(responseFacturaDTO,response.getBody());
    }
    @Test
    public void GetAllFacturasEmptyTest() {
        Mockito.when(facturaService.getAllFacturas()).thenReturn(Collections.emptyList());
        ResponseEntity<java.util.List<ResponseFacturaDTO>> response=facturaController.getAllFacturas();
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void GetAllFacturasTest() {
        List<ResponseFacturaDTO> facturas= List.of(new ResponseFacturaDTO(),new ResponseFacturaDTO());
        Mockito.when(facturaService.getAllFacturas()).thenReturn(facturas);
        ResponseEntity<java.util.List<ResponseFacturaDTO>> response=facturaController.getAllFacturas();
        assertEquals("200 OK",response.getStatusCode().toString());
        assertNotNull(response.getBody());
    }
    @Test
    public void GetFacturaByIdNotFoundTest() {
        Long id=1L;
        Mockito.when(facturaService.getFacturaById(id)).thenReturn(java.util.Optional.empty());
        ResponseEntity<ResponseFacturaDTO> response=facturaController.getFacturaById(id);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void GetFacturaByIdTest() {
        Long id=1L;
        ResponseFacturaDTO responseFacturaDTO=new ResponseFacturaDTO();
        Mockito.when(facturaService.getFacturaById(id)).thenReturn(Optional.of(responseFacturaDTO));
        ResponseEntity<ResponseFacturaDTO> response=facturaController.getFacturaById(id);
        assertEquals(200,response.getStatusCode().value());
        assertEquals(responseFacturaDTO,response.getBody());
        assertEquals(true,response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }
    @Test
    public void DeleteFacturaByIdTest() {
        Long id = 1L;
        Mockito.doNothing().when(facturaService).deleteFacturaById(id);
        ResponseEntity<Void> response = facturaController.deleteFacturaById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}
