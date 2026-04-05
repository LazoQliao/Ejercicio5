package com.example.videojuegos.dto;

import com.example.videojuegos.modelo.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioRequest {

    private String rut;

    private String nombreCompleto;
    
    private Rol rol;

}
