package com.example.videojuegos.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.videojuegos.dto.VideojuegoRequestDTO;
import com.example.videojuegos.dto.VideojuegoResponseDTO;

public interface  VideojuegoService {
    VideojuegoResponseDTO crearVideojuego(VideojuegoRequestDTO requestDTO);
    
    VideojuegoResponseDTO buscarId(Long id);
    
    Page<VideojuegoResponseDTO> obtenerTodosLosVideojuegos(Pageable pageable);
    



















}
