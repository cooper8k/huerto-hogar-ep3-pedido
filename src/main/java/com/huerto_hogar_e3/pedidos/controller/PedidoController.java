package com.huerto_hogar_e3.pedidos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huerto_hogar_e3.pedidos.model.Pedido;
import com.huerto_hogar_e3.pedidos.service.PedidoService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/pedido")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listar(){
        try {
            List<Pedido> pedidos = pedidoService.pedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            // Log the exception and return 500 with a short message
            log.error("Error obteniendo lista de pedidos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido){
        Pedido nuevPedido = pedidoService.agregarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevPedido);
    }
}
