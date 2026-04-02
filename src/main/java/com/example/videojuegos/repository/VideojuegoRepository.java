package com.example.videojuegos.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.videojuegos.modelo.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long>  {



}
