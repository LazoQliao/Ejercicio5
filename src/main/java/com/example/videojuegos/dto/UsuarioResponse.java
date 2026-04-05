package com.example.videojuegos.dto;

import java.time.LocalDateTime;

import com.example.videojuegos.modelo.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {


    private long id;

    private String rut;

    private String nombreCompleto;
    
    private Rol rol;

    private LocalDateTime fechaCreacionUsuario;
    



}
