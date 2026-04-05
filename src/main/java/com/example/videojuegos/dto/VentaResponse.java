package com.example.videojuegos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {
    private Long id;
    private String rutComprador;
    private Long idVideojuego; // Fíjate que aquí dice idVideojuego
    private String tituloVideojuego;
    private Double precioUnitario;
    private Integer cantidad; // Ajusta a cantidadStock si lo necesitas llamar así aquí también
    private Double total;
    private LocalDateTime fechaCompra;
}
