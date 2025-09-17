package com.factura.demo;

import com.factura.demo.controller.ProductoController;
import com.factura.demo.dto.ProductoDTO;
import com.factura.demo.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductoControllerTest {
    @Mock
    private ProductoService productoService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ProductoController productoController;
    @BeforeEach
    public void init() {
        // Inicializa los mocks antes de cada prueba
        // Esto es necesario para que las anotaciones @Mock y @InjectMocks funcionen correctamente
        org.mockito.MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createProductoTest(){
        ProductoDTO productoDTO = new ProductoDTO(1L, "Producto 1", 100.0, "uno");
        when(productoService.save(any(ProductoDTO.class))).thenReturn(productoDTO);
        ResponseEntity<ProductoDTO> response=  productoController.createProducto(productoDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Producto 1", response.getBody().getNombre());
        assertEquals(100.0, response.getBody().getPrecio());
    }
    @Test
    public void getAllProductosEmptyTest(){
        when(productoService.listAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<ProductoDTO>> response=  productoController.getAllProductos();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    public void getAllProductosTest(){
        List<ProductoDTO> productos = new ArrayList<>();
        productos.add(new ProductoDTO(1L, "Producto 1", 100.0, "uno"));
        when(productoService.listAll()).thenReturn(productos);
        ResponseEntity<List<ProductoDTO>> response=  productoController.getAllProductos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
    @Test
    public void getProductoByIdTest(){
        Long id=1L;
        ProductoDTO productoDTO = new ProductoDTO(id, "Producto 1", 100.0, "uno");
        when(productoService.getById(id)).thenReturn(Optional.of(productoDTO));
        ResponseEntity<?> response=  productoController.getProductoById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().toString().contains("Producto 1"));
    }
    @Test
    public void getProductoByIdTestNotFound(){
        Long id=1L;
        when(productoService.getById(id)).thenReturn(Optional.empty());
        ResponseEntity<?> response = productoController.getProductoById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    public void updateProductoTest(){
        Long id=1L;
        ProductoDTO productoDTO = new ProductoDTO(id, "Producto 1", 100.0, "uno");
        when(productoService.update(id, productoDTO)).thenReturn(productoDTO);
        ProductoDTO response=  productoController.updateProducto(id, productoDTO);
        assertNotNull(response);
        assertEquals("Producto 1", response.getNombre());
        assertEquals(100.0, response.getPrecio());
    }
    @Test
    public void deleteProductoTest(){
        Long id=1L;
        when(productoService.delete(id)).thenReturn(true);
        boolean response=  productoController.deleteProducto(id);
        assertTrue(response);
    }
    @Test
    public void deleteProductoTestFalse(){
        Long id=1L;
        when(productoService.delete(id)).thenReturn(false);
        boolean response=  productoController.deleteProducto(id);
        assertFalse(response);
    }
}
