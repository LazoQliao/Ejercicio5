package com.example.videojuegos.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "videojuegos")
public class Videojuego {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
