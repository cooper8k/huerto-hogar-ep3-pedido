package com.huerto_hogar_e3.pedidos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
   private Long id_pedido;
   private LocalDateTime fecha_pedido;
   private String estado;
   private Double subtotal;
   private String observaciones;
   private Integer usuarioId;
   private Integer carritoId;
}
