package com.huerto_hogar_e3.pedidos.model;

import java.time.LocalDateTime;

// No almacenar DTOs de otros servicios como columnas JPA

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fecha_pedido;

    @Column(name = "estado",nullable = false)
    private String estado;

    @Column(name = "subtotal",nullable = false)
    private Double subtotal;

    @Column(name = "observaciones",nullable = false)
    private String observaciones;
    

    @Column(name = "usuario_id", nullable = true)
    private Integer usuarioId;

    @Column(name = "carrito_id", nullable = true)
    private Integer carritoId;
    
}
