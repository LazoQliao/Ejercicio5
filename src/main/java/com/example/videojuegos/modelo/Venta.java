package com.example.videojuegos.modelo;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Venta")
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String rutComprador;

    private Long idVideojuego;

    private String tituloVideojuego;

    private double precioUnitario;
    private double total;

    private Integer cantidadStock;

    private LocalDateTime fechaDeCompra;

public void setIdVideojuego(Long idVideojuego) {
    System.out.println("LOG: El Setter de ID ha sido llamado con: " + idVideojuego);
    this.idVideojuego = idVideojuego;
}
@PrePersist
    protected void onCreate() {
        this.fechaDeCompra = LocalDateTime.now();
    }
}
