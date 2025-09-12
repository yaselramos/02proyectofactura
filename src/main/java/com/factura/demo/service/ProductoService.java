package com.factura.demo.service;

import com.factura.demo.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    ProductoDTO save(ProductoDTO productoDTO);
    List<ProductoDTO> listAll();
    ProductoDTO getById(Long id);
    ProductoDTO update(Long id,ProductoDTO productoDTO);
    boolean delete(Long id);
}
