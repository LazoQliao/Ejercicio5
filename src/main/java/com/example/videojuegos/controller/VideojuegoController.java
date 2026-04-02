package com.example.videojuegos.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.videojuegos.Services.VideojuegoService;
import com.example.videojuegos.dto.VideojuegoRequestDTO;
import com.example.videojuegos.dto.VideojuegoResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/videojuegos")
@RequiredArgsConstructor
@Slf4j


public class VideojuegoController {
    private final VideojuegoService vService;

    @PostMapping("/crear")
    public ResponseEntity<VideojuegoResponseDTO> añadirVideojuego (@RequestBody VideojuegoRequestDTO request){
        log.info("POST /api/videojuegos/añadir: {}", request.getTitulo());
        VideojuegoResponseDTO juegoCreado= vService.crearVideojuego(request);
        return new ResponseEntity<>(juegoCreado, HttpStatus.CREATED);

    }

    @GetMapping("/obtener")
    public ResponseEntity<Page<VideojuegoResponseDTO>> obtenerTodo(Pageable pageable){
        log.info("GET /api/videojuegos/todos");
        Page<VideojuegoResponseDTO> v = vService.obtenerTodosLosVideojuegos(pageable);
        return ResponseEntity.ok(v);
    
    }

    @GetMapping("/{id}")
    public ResponseEntity <VideojuegoResponseDTO> obtenerPorId(@PathVariable Long id){
        log.info("GET /api/videojuegos/{}", id);
        VideojuegoResponseDTO v = vService.buscarId(id);
        return ResponseEntity.ok(v);
    }
}

