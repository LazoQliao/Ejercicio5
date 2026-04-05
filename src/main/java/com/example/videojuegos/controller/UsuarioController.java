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

import com.example.videojuegos.Services.UsuarioService;
import com.example.videojuegos.dto.UsuarioRequest;
import com.example.videojuegos.dto.UsuarioResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {
    private final UsuarioService userService;


    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponse> crearUsuario (@RequestBody UsuarioRequest request){
        log.info("POST / api/usuarios/crear: {}", request.getNombreCompleto());
        UsuarioResponse userCreado = userService.crearUsuario(request);
        return new ResponseEntity<>(userCreado, HttpStatus.CREATED);
    }

    @GetMapping("/obtener")
    public ResponseEntity<Page<UsuarioResponse>> obtenerTodo(Pageable pageable){
        log.info("GET /api/usuarios/todos");
        Page<UsuarioResponse> u = userService.obtenerTodosLosUsuarios(pageable);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id){
        log.info("GET /api/usuarios/{}");
        UsuarioResponse u = userService.buscarId(id);
        return ResponseEntity.ok(u);

    }


}
