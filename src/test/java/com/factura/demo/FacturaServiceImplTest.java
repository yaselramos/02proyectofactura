package com.factura.demo;

import com.factura.demo.dto.ProductoDTO;
import com.factura.demo.dto.RequestDetalleFacturaDTO;
import com.factura.demo.dto.RequestFacturaDTO;
import com.factura.demo.dto.ResponseFacturaDTO;
import com.factura.demo.model.DetalleFactura;
import com.factura.demo.model.Factura;
import com.factura.demo.model.Producto;
import com.factura.demo.repository.DetalleFacturaRepository;
import com.factura.demo.repository.FacturaRepository;
import com.factura.demo.repository.ProductoRepository;
import com.factura.demo.service.FacturaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FacturaServiceImplTest {
    @Mock
    FacturaRepository facturaRepository;
    @Mock
    DetalleFacturaRepository detalleFacturaRepository;
    @Mock
    ProductoRepository productoRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    FacturaServiceImpl facturaService;
    List<Factura> listfacturas=new ArrayList<>();
    List<ResponseFacturaDTO> listfacturasDTO=new ArrayList<>();
    @BeforeEach
    public void init() {
        // Inicializa los mocks antes de cada prueba
        // Esto es necesario para que las anotaciones @Mock y @InjectMocks funcionen correctamente
        org.mockito.MockitoAnnotations.openMocks(this);
        listfacturas= Arrays.asList(
                new Factura(1L,"FACT-001", BigDecimal.valueOf(500.0),BigDecimal.valueOf(575.0),null,null),
                new Factura(2L,"FACT-002", BigDecimal.valueOf(500.0),BigDecimal.valueOf(575.0),null,null)
               );
        // convertir listfacturas a listfacturasDTO
        ResponseFacturaDTO uno=new ResponseFacturaDTO(1L,"FACT-001", BigDecimal.valueOf(500.0),BigDecimal.valueOf(575.0),null,null);
        ResponseFacturaDTO dos=new ResponseFacturaDTO(2L,"FACT-002", BigDecimal.valueOf(500.0),BigDecimal.valueOf(575.0),null,null);
        listfacturasDTO= Arrays.asList(uno,dos

        );

    }
    @Test
    public void getAllFacturas(){
        when(facturaRepository.findAll()).thenReturn(listfacturas);
        when(modelMapper.map(listfacturas.get(0), ResponseFacturaDTO.class)).thenReturn(listfacturasDTO.get(0));
        when(modelMapper.map(listfacturas.get(1), ResponseFacturaDTO.class)).thenReturn(listfacturasDTO.get(1));
        // Ejecutamos el método
        List<ResponseFacturaDTO> resultado = facturaService.getAllFacturas();
        // Verificaciones
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("FACT-001", resultado.get(0).getNumeroFactura());
    }
    @Test
    public void getFacturaById(){
        Long id=1L;
        when(facturaRepository.findById(id)).thenReturn(Optional.of(listfacturas.get(0)));
        when(modelMapper.map(listfacturas.get(0), ResponseFacturaDTO.class)).thenReturn(listfacturasDTO.get(0));
        // Ejecutamos el método
        Optional<ResponseFacturaDTO> resultado = facturaService.getFacturaById(id);
        // Verificaciones
        assertNotNull(resultado);
        assertEquals(true, resultado.isPresent());
        assertEquals("FACT-001", resultado.get().getNumeroFactura());
    }
    @Test
    public void deleteFacturaById(){
        Long id=1L;
        Optional<Factura> optionalFactura=Optional.of(listfacturas.get(0));
        when(facturaRepository.findById(id)).thenReturn(Optional.of(listfacturas.get(0)));
        // Ejecutamos el método
        facturaService.deleteFacturaById(id);
    }
    @Test
    public void deleteFacturaById_NotFound() {
        Long id = 99L;
        // Mock findById devuelve vacío
        when(facturaRepository.findById(id)).thenReturn(Optional.empty());
        // Ejecutamos el método
        facturaService.deleteFacturaById(id);
        // Verificamos que deleteById nunca se llamó
        verify(facturaRepository, never()).deleteById(anyLong());
    }
    @Test
    void testSaveFactura_Success() {
        RequestFacturaDTO request = new RequestFacturaDTO();
        Set<RequestDetalleFacturaDTO> detalles = new HashSet<>();
        RequestDetalleFacturaDTO detalleDTO = new RequestDetalleFacturaDTO(1L,2);

        request.setNumeroFactura("FACT-001");
        detalles.add(detalleDTO);
        request.setDetalles(detalles);

        Factura factura = new Factura();
        when(modelMapper.map(request, Factura.class)).thenReturn(factura);

        Producto producto = new Producto(1L, "Laptop", 500.0, null, null, null);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Factura savedFactura = new Factura(1L,"FACT-001", BigDecimal.valueOf(1000),BigDecimal.valueOf(1190),null,null);
        when(facturaRepository.save(any(Factura.class))).thenReturn(savedFactura);

        ResponseFacturaDTO responseDTO = new ResponseFacturaDTO(1L,"FACT-001", BigDecimal.valueOf(1000),BigDecimal.valueOf(1190),null,null);
        when(modelMapper.map(savedFactura, ResponseFacturaDTO.class)).thenReturn(responseDTO);

        ResponseFacturaDTO resultado = facturaService.saveFactura(request);

        assertNotNull(resultado);
        assertEquals("FACT-001", resultado.getNumeroFactura());
        assertEquals(BigDecimal.valueOf(1000), resultado.getSubtotal());
        assertEquals(BigDecimal.valueOf(1190), resultado.getTotal());
    }

    @Test
    void testSaveFactura_ProductoNotFound() {
        RequestFacturaDTO request = new RequestFacturaDTO();
        request.setNumeroFactura("FACT-002");
        Set<RequestDetalleFacturaDTO> detalles = new HashSet<>();
        RequestDetalleFacturaDTO detalleDTO = new RequestDetalleFacturaDTO();
        detalleDTO.setIdProducto(99L);
        detalleDTO.setCantidad(1);
        detalles.add(detalleDTO);
        request.setDetalles(detalles);

        Factura factura = new Factura();

        when(modelMapper.map(request, Factura.class)).thenReturn(factura);

        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> facturaService.saveFactura(request));
        assertTrue(ex.getMessage().contains("Producto no encontrado con ID: 99"));
    }



}
