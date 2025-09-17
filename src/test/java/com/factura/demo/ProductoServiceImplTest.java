package com.factura.demo;

import com.factura.demo.dto.ProductoDTO;
import com.factura.demo.model.Producto;
import com.factura.demo.repository.ProductoRepository;
import com.factura.demo.service.ProductoService;
import com.factura.demo.service.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

public class ProductoServiceImplTest {
    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductoServiceImpl productoService; // la clase donde está el método save()

    private ProductoDTO productoDTO;
    private Producto producto;
    private List<ProductoDTO> productodtoList;
    private List<Producto> productoList;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        productodtoList=new ArrayList<>();
        productoList=new ArrayList<>();
        productoDTO = new ProductoDTO();
        productoDTO.setId(1L);
        productoDTO.setNombre("Laptop");
        productoDTO.setPrecio(1200.0);



        productodtoList.add(productoDTO);
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Laptop");
        producto.setPrecio(1200.0);
        productoList.add(producto);

    }
    @Test
    public void saveTest(){
        // mock: DTO -> Entidad
        when(modelMapper.map(productoDTO, Producto.class)).thenReturn(producto);
        // mock: repository.save
        when(productoRepository.save(producto)).thenReturn(producto);
        // mock: Entidad -> DTO
        when(modelMapper.map(producto, ProductoDTO.class)).thenReturn(productoDTO);
        // ejecutar método
        ProductoDTO resultado = productoService.save(productoDTO);
        // verificar
        assertNotNull(resultado);
        assertEquals(productoDTO.getNombre(), resultado.getNombre());
        assertEquals(productoDTO.getPrecio(), resultado.getPrecio());
    }
    @Test
    public void listAllTest(){
        // Mock: repositorio devuelve entidades
        when(productoRepository.findAll()).thenReturn(productoList);
        // Mock: modelMapper convierte entidades a DTO
        when(modelMapper.map(producto, ProductoDTO.class)).thenReturn(productoDTO);
        // Ejecutamos el método
        List<ProductoDTO> resultado = productoService.listAll();
        // Verificaciones
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laptop", resultado.get(0).getNombre());
    }
    @Test
    public void getById(){
        // Mock: repositorio devuelve entidad por ID
        when( productoRepository.findById(1L)).thenReturn(Optional.of(producto));
      //  when( productoRepository.findById(1L).isPresent()).thenReturn(true);
        when(modelMapper.map(producto, ProductoDTO.class)).thenReturn(productoDTO);
        Optional<ProductoDTO>  resultado=productoService.getById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
        assertEquals("Laptop", resultado.get().getNombre());
    }

    @Test
    void updateTest() {
        // DTO de entrada con datos nuevos
        ProductoDTO productoDTOBody = new ProductoDTO();
        productoDTOBody.setNombre("Laptop Gamer");
        productoDTOBody.setPrecio(1500.0);
        // DTO esperado de salida
        ProductoDTO productoDTOEsperado = new ProductoDTO(1L, "Laptop Gamer", 1500.0, "");
        // Mock findById
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        // Mock save
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        // Mock map
        when(modelMapper.map(producto, ProductoDTO.class)).thenReturn(productoDTOEsperado);
        // Ejecutamos el método
        ProductoDTO resultado = productoService.update(1L, productoDTOBody);
        // Validaciones
        assertEquals(1L, resultado.getId());
        assertEquals("Laptop Gamer", resultado.getNombre());
        assertEquals(1500.0, resultado.getPrecio());
    }

    @Test
    void testUpdateProductoNotFound() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            productoService.update(99L, new ProductoDTO());
        });
        // Validamos que se lance la excepción
        verify(productoRepository, never()).save(any(Producto.class));
    }
    @Test
    void deleteTest(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        boolean resultado=productoService.delete(1L);
        assertTrue(resultado);
        verify(productoRepository, times(1)).delete(producto);
    }
    @Test
    void deleteNotFoundTest(){
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        boolean resultado=productoService.delete(99L);
        assertFalse(resultado);
        verify(productoRepository, never()).delete(producto);
    }
}
