package com.factura.demo.controller;

import com.factura.demo.dto.ProductoDTO;
import com.factura.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productoDTO));
    }
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos(){
        if (productoService.listAll().isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(productoService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        try {
            productoService.getById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(productoService.getById(id));
    }
    @PutMapping("/{id}")
    public ProductoDTO updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return productoService.update(id, productoDTO);
    }
    @DeleteMapping("/{id}")
    public boolean deleteProducto(@PathVariable Long id) {
        return productoService.delete(id);
    }

}
