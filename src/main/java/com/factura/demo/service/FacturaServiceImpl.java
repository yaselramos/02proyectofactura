package com.factura.demo.service;

import com.factura.demo.dto.RequestDetalleFacturaDTO;
import com.factura.demo.dto.RequestFacturaDTO;
import com.factura.demo.dto.ResponseFacturaDTO;
import com.factura.demo.model.DetalleFactura;
import com.factura.demo.model.Factura;
import com.factura.demo.model.Producto;
import com.factura.demo.repository.DetalleFacturaRepository;
import com.factura.demo.repository.FacturaRepository;
import com.factura.demo.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    DetalleFacturaRepository detalleFacturaRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<ResponseFacturaDTO> getAllFacturas() {
        return facturaRepository.findAll()
                .stream()
                .map(factura -> modelMapper.map(factura, ResponseFacturaDTO.class))
                .toList();
    }
    public Optional<ResponseFacturaDTO> getFacturaById(Long id) {
        return facturaRepository.findById(id)
                .map(factura -> modelMapper.map(factura, ResponseFacturaDTO.class));
    }
    public void deleteFacturaById(Long id) {
     Optional<Factura> obj=   facturaRepository.findById(id);
     if (obj.isPresent())
         facturaRepository.deleteById(id);
    }
    @Transactional
    public ResponseFacturaDTO saveFactura(RequestFacturaDTO requestFacturaDTO) {
        Factura factura = modelMapper.map(requestFacturaDTO, Factura.class);
        BigDecimal subtotalfactura=BigDecimal.ZERO;
        Set<DetalleFactura> detalles = new HashSet<>();

        factura.setNumeroFactura(requestFacturaDTO.getNumeroFactura());
        for (RequestDetalleFacturaDTO detalleFacturaDTO:requestFacturaDTO.getDetalles()){
            Producto producto = productoRepository.findById(detalleFacturaDTO.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleFacturaDTO.getIdProducto()));
            BigDecimal totalproducto = BigDecimal.valueOf(producto.getPrecio()).multiply(BigDecimal.valueOf(detalleFacturaDTO.getCantidad()));
            subtotalfactura = subtotalfactura.add(totalproducto);
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setIdProducto(producto.getId());
            detalleFactura.setPrecio(BigDecimal.valueOf(producto.getPrecio()));
            detalleFactura.setCantidad(detalleFacturaDTO.getCantidad());
            detalleFactura.setTotal(totalproducto);
            detalleFactura.setFactura(factura);
            detalles.add(detalleFactura);
        }
        factura.setDetalles(detalles);
       // factura.setFechaCreacion(LocalDateTime.now());
        factura.setSubtotal(subtotalfactura);
        factura.setTotal(subtotalfactura.add(subtotalfactura.multiply(factura.getIVA())));
        Factura savedFactura = facturaRepository.save(factura);
        return modelMapper.map(savedFactura, ResponseFacturaDTO.class);
    }

}
