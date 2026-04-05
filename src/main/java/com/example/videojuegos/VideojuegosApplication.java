package com.example.videojuegos;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.videojuegos.modelo.Rol;
import com.example.videojuegos.modelo.Usuario;
import com.example.videojuegos.modelo.Venta;
import com.example.videojuegos.modelo.Videojuego;
import com.example.videojuegos.repository.UsuarioRepository;
import com.example.videojuegos.repository.VentaRepository;
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
            log.info(">>>> Iniciando carga de datos de prueba de Videojuegos...");

            if (repository.count() == 0) {
                Videojuego juego1 = new Videojuego();
                juego1.setTitulo("The Legend of Zelda: Breath of the Wild");
                juego1.setDesarrolador("Nintendo");
                juego1.setGenero("Acción-Aventura");
                juego1.setPrecio(59.99);
                juego1.setStock(50);
                juego1.setFechaLanzamiento(LocalDateTime.now());

                Videojuego juego2 = new Videojuego();
                juego2.setTitulo("Elden Ring");
                juego2.setDesarrolador("FromSoftware");
                juego2.setGenero("RPG");
                juego2.setPrecio(49.90);
                juego2.setStock(20);
                juego2.setFechaLanzamiento(LocalDateTime.now());

                repository.saveAll(List.of(juego1, juego2));
                log.info(">>>> ¡Se han insertado {} videojuegos de prueba!", repository.count());
            } else {
                log.info(">>>> La base de datos ya tiene videojuegos, saltando.");
            }
        };
    }

    // --- CARGA DE USUARIOS ---
    @Bean
    CommandLineRunner initUsuarios(UsuarioRepository repository) {
        return args -> {
            log.info(">>>> Iniciando carga de datos de prueba de Usuarios...");

            if (repository.count() == 0) {
                Usuario u1 = new Usuario();
                u1.setRut("12.345.678-5");
                u1.setNombreCompleto("Juan Pérez González");
                u1.setRol(Rol.Admin); // Verifica si es Admin o ADMIN

                Usuario u2 = new Usuario();
                u2.setRut("19.876.543-K");
                u2.setNombreCompleto("María Ignacia Soto");
                u2.setRol(Rol.Cliente); // Verifica si es Cliente o CLIENTE

                repository.saveAll(List.of(u1, u2));
                log.info(">>>> Usuarios de prueba cargados correctamente.");
            } else {
                log.info(">>>> La base de datos ya tiene usuarios, saltando.");
            }
        };
    }
    @Bean
CommandLineRunner initVentas(VentaRepository repository) {
    return args -> {
        log.info(">>>> Iniciando carga de historial de ventas de prueba...");

        if (repository.count() == 0) {
            // Venta 1: Juan Pérez compra 2 unidades de Zelda
            Venta v1 = new Venta();
            v1.setRutComprador("12.345.678-5"); // RUT de Juan
            v1.setIdVideojuego(1L);             // ID de Zelda
            v1.setTituloVideojuego("The Legend of Zelda: Breath of the Wild");
            v1.setPrecioUnitario(59.99);
            v1.setCantidadStock(2);
            v1.setTotal(59.99 * 2);

            // Venta 2: María Soto compra 1 unidad de Elden Ring
            Venta v2 = new Venta();
            v2.setRutComprador("19.876.543-K"); // RUT de María
            v2.setIdVideojuego(2L);             // ID de Elden Ring
            v2.setTituloVideojuego("Elden Ring");
            v2.setPrecioUnitario(49.90);
            v2.setCantidadStock(1);
            v2.setTotal(49.90);

            repository.saveAll(List.of(v1, v2));
            log.info(">>>> Historial de ventas cargado exitosamente.");
        } else {
            log.info(">>>> El historial de ventas ya tiene datos, saltando.");
        }
    };
}
    
    }

