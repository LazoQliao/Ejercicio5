package com.example.videojuegos.dto;

import java.time.LocalDateTime;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideojuegoRequestDTO {
    private String titulo;
    private String desarrolador;
    private String plataforma;
    private String genero;
    private String clasificacion;
    private double precio;
    private Integer stock;
    private LocalDateTime fechaLanzamiento;


}
