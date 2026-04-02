package com.example.videojuegos;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.videojuegos.modelo.Videojuego;
import com.example.videojuegos.repository.VideojuegoRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class VideojuegosApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideojuegosApplication.class, args);
	
	
	
	
	
	}
@Bean
    CommandLineRunner initDatabase(VideojuegoRepository repository) {
        return args -> {
            log.info(">>>> Iniciando carga de datos de prueba...");

            // Solo insertamos si la base de datos está vacía para no duplicar cada vez que reinicies
            if (repository.count() == 0) {
                
                Videojuego juego1 = new Videojuego();
                juego1.setTitulo("The Legend of Zelda: Breath of the Wild");
                juego1.setDesarrolador("Nintendo");
                juego1.setGenero("Acción-Aventura");
                juego1.setPrecio(59.99);
                juego1.setStock(50);
                juego1.setFechaLanzamiento(LocalDateTime.now());
                // Asegúrate de que los valores de tus Enums existan en tu código
                // juego1.setPlataforma(Plataforma.NINTENDO_SWITCH); 
                // juego1.setClasificacion(Clasificacion.EVERYONE);

                Videojuego juego2 = new Videojuego();
                juego2.setTitulo("Elden Ring");
                juego2.setDesarrolador("FromSoftware");
                juego2.setGenero("RPG");
                juego2.setPrecio(49.90);
                juego2.setStock(20);
                juego2.setFechaLanzamiento(LocalDateTime.now());

                // Guardamos la lista completa
                repository.saveAll(List.of(juego1, juego2));

                log.info(">>>> ¡Se han insertado {} videojuegos de prueba!", repository.count());
            } else {
                log.info(">>>> La base de datos ya tiene datos, saltando inicialización.");
            }
        };
    }

}
