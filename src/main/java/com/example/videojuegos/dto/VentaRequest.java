package com.example.videojuegos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {
    private String rutComprador;

    private Long idVideojuego;


    private Integer cantidadStock;

    
}
