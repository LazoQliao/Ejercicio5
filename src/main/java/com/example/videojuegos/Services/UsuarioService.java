package com.example.videojuegos.Services;

import org.springframework.data.domain.*;

import com.example.videojuegos.dto.UsuarioRequest;
import com.example.videojuegos.dto.UsuarioResponse;

public interface UsuarioService {

    UsuarioResponse crearUsuario(UsuarioRequest requestDTO);

    UsuarioResponse buscarId(Long id);

    Page<UsuarioResponse> obtenerTodosLosUsuarios (Pageable pageable);

    void eliminarUsuario (Long id);
    

}
