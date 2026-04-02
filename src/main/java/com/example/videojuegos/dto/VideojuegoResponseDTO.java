package com.example.videojuegos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class VideojuegoResponseDTO {
    private long id;


    private String titulo;
    private String desarrolador;
    private String plataforma;
    private String genero;
    private String clasificacion;
    private double precio;
    private Integer stock;
    private LocalDateTime fechaLanzamiento;

}
