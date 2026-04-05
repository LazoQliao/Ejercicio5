package com.example.videojuegos.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.videojuegos.dto.VentaRequest;
import com.example.videojuegos.dto.VentaResponse;


public interface  VentaService {

    VentaResponse crearVenta(VentaRequest requestDTO);

    VentaResponse buscarVenta(Long id);

    Page<VentaResponse> obtenerTodasLasVentas (Pageable pageable);

    
}
