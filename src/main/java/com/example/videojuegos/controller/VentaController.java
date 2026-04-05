package com.example.videojuegos.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.videojuegos.Services.VentaService;
import com.example.videojuegos.dto.VentaRequest;
import com.example.videojuegos.dto.VentaResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/venta")
@RequiredArgsConstructor
@Slf4j
public class VentaController {
    private final VentaService ventaService;

    /*@PostMapping("/comprar")
    public ResponseEntity<VentaResponse> crearVenta(@RequestBody VentaRequest request){
        log.info("POST /api/venta/comprar: {}", request.getRutComprador());
        VentaResponse ventaCreada= ventaService.crearVenta(request);
        return new ResponseEntity<>(ventaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/obtener")
    public ResponseEntity<Page<VentaResponse>> obtenerTodo(@RequestBody Pageable pageable){
        log.info("GET /api/venta/todos");
        Page<VentaResponse> v = ventaService.obtenerTodasLasVentas(pageable);
        return ResponseEntity.ok(v);
    }

*/
    @PostMapping("/comprar")
    public ResponseEntity<VentaResponse> registrarVenta(@RequestBody VentaRequest request) {
        // Usamos HttpStatus.CREATED (201) porque estamos creando un nuevo registro
        return new ResponseEntity<>(ventaService.crearVenta(request), HttpStatus.CREATED);
    }

    // 2. LISTAR TODAS LAS VENTAS (GET)
    // URL: http://localhost:8080/api/ventas
@GetMapping("/obtener")
public ResponseEntity<Page<VentaResponse>> listarTodo(Pageable pageable) {
    // Retorna un objeto Page que incluye: contenido, total de páginas, total de elementos, etc.
    return ResponseEntity.ok(ventaService.obtenerTodasLasVentas(pageable));
}



}
