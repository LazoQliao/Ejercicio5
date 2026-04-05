    package com.example.videojuegos.Services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.videojuegos.dto.VideojuegoRequestDTO;
import com.example.videojuegos.dto.VideojuegoResponseDTO;
import com.example.videojuegos.modelo.Videojuego;
import com.example.videojuegos.repository.VideojuegoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional



public class VideojuegoServiceImpl implements VideojuegoService {
    private final VideojuegoRepository videojuegoRepository;

    private VideojuegoResponseDTO entidadADTO(Videojuego videojuego){
        return VideojuegoResponseDTO.builder()
        .id(videojuego.getId())
        .titulo(videojuego.getTitulo())
        .desarrolador((videojuego.getDesarrolador()))
        .plataforma((videojuego.getPlataforma()))
        .genero(videojuego.getGenero())
        .clasificacion(videojuego.getClasificacion())
        .precio(videojuego.getPrecio())
        .stock(videojuego.getStock())
        .fechaLanzamiento(videojuego.getFechaLanzamiento())
        .build();
    }

    private Videojuego dtoAEntidad(VideojuegoRequestDTO dto){
        Videojuego videojuego = new Videojuego();
        videojuego.setTitulo(dto.getTitulo());
        videojuego.setDesarrolador(dto.getDesarrolador());
        videojuego.setPlataforma(dto.getPlataforma());
        videojuego.setGenero(dto.getGenero());
        videojuego.setClasificacion(dto.getClasificacion());
        videojuego.setPrecio(dto.getPrecio());
        videojuego.setStock(dto.getStock());
        videojuego.setFechaLanzamiento(dto.getFechaLanzamiento());
        return videojuego;
    }

    @Override
    public VideojuegoResponseDTO crearVideojuego(VideojuegoRequestDTO request){
        log.info("Añadiendo nuevo videojuego: {}",request.getTitulo());

        Videojuego v = dtoAEntidad(request);
        Videojuego vguardado= videojuegoRepository.save(v);
     
        return entidadADTO(vguardado);
    }
    @Override
    public VideojuegoResponseDTO buscarId(Long id){
        log.info("buscando videojuego por id: {}", id);
        Videojuego v = videojuegoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("error"));
        return entidadADTO(v);
    }
    @Override
    public Page<VideojuegoResponseDTO> obtenerTodosLosVideojuegos(Pageable pageable){
        Page<Videojuego> videojuegosPage= videojuegoRepository.findAll(pageable);
        return videojuegosPage.map(this::entidadADTO);
    }
}
