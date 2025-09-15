package com.factura.demo.service;

import com.factura.demo.dto.ProductoDTO;
import com.factura.demo.model.Producto;
import com.factura.demo.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto=productoRepository.save(modelMapper.map(productoDTO, Producto.class));
        return modelMapper.map(producto, ProductoDTO.class);
    }

    @Override
    public List<ProductoDTO> listAll() {
            return productoRepository.findAll().stream().map(
                    producto -> modelMapper.map(producto, ProductoDTO.class)).toList();


    }

    @Override
    public ProductoDTO getById(Long id) {
        return modelMapper.map(productoRepository.findById(id).orElseThrow(), ProductoDTO.class);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO productoDTO) {
        return modelMapper.map(productoRepository.findById(id).map(obj ->{
            obj.setNombre(productoDTO.getNombre());
            obj.setPrecio(productoDTO.getPrecio());
            obj.setDetalle(productoDTO.getDetalle());
            return productoRepository.save(obj);
        }),ProductoDTO.class);


    }

    @Override
    public boolean delete(Long id) {
        return productoRepository.findById(id).map(producto -> {
            productoRepository.delete(producto);
            return true;
        }).orElse(false);
    }
}
