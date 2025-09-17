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
    public Optional<ProductoDTO> getById(Long id) {
        Optional<Producto> producto=productoRepository.findById(id);
        if (producto.isPresent()){
            return Optional.of(modelMapper.map(producto.orElseThrow(), ProductoDTO.class));
        }
     return Optional.empty();
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto not found"));
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setDetalle(productoDTO.getDetalle());
        Producto updatedProducto = productoRepository.save(producto);
       return modelMapper.map(updatedProducto, ProductoDTO.class);
//        return modelMapper.map(productoRepository.findById(id).map(obj ->{
//            obj.setNombre(productoDTO.getNombre());
//            obj.setPrecio(productoDTO.getPrecio());
//            obj.setDetalle(productoDTO.getDetalle());
//            return productoRepository.save(obj);
//        }),ProductoDTO.class);


    }

    @Override
    public boolean delete(Long id) {
        return productoRepository.findById(id).map(producto -> {
            productoRepository.delete(producto);
            return true;
        }).orElse(false);
    }
}
