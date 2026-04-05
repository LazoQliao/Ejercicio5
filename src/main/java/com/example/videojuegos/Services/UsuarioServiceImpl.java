package com.example.videojuegos.Services;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.videojuegos.dto.UsuarioRequest;
import com.example.videojuegos.dto.UsuarioResponse;
import com.example.videojuegos.modelo.Usuario;
import com.example.videojuegos.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional

public class UsuarioServiceImpl implements UsuarioService {



    private final UsuarioRepository usuarioRepository;


    private UsuarioResponse entidadADTO (Usuario usuario){
        return UsuarioResponse.builder()
        .id(usuario.getId())
        .rut(usuario.getRut())
        .nombreCompleto(usuario.getNombreCompleto())
        .rol(usuario.getRol())
        .fechaCreacionUsuario(usuario.getFechaCreacionUsuario())
        .build();
        
    }

    


    private Usuario dtoAEntidad(UsuarioRequest dto){
        Usuario user = new Usuario();
        user.setRut(dto.getRut());
        user.setNombreCompleto(dto.getNombreCompleto());
        user.setRol(dto.getRol());
        
        return user;
    }


    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request){
        log.info("Creando Usuario: {}", request.getNombreCompleto());

        Usuario user = dtoAEntidad(request);
        user.setFechaCreacionUsuario(LocalDateTime.now());
        Usuario nuevoUser = usuarioRepository.save(user);
        return entidadADTO(nuevoUser);


    }



    @Override
    public UsuarioResponse buscarId(Long id){
        log.info("buscando usuario por id: {}", id);
        Usuario user = usuarioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException ("error"));
        return entidadADTO(user);
    }


    @Override
    public Page<UsuarioResponse> obtenerTodosLosUsuarios (Pageable pageable){
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        return usuarioPage.map(this::entidadADTO);
    }
    

    @Override
    public void eliminarUsuario (Long id){
        log.info("Eliminando usuario por id {},",id);
        
        if(!usuarioRepository.existsById(id)){
            log.error("No existe el usuario");
            throw new RuntimeException("Usuario no encontrado"+ id);
        }
        
        usuarioRepository.deleteById(id);
        log.info("usuario eliminado correctamente");

    }



}
