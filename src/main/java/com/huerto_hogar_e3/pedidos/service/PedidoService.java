package com.huerto_hogar_e3.pedidos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huerto_hogar_e3.pedidos.model.Pedido;
import com.huerto_hogar_e3.pedidos.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    // listar pedidos
    public List<Pedido> pedidos(){
        return pedidoRepository.listaPedidos();
    }

    public Pedido agregarPedido(Pedido pedido){
        // Si no llega fecha_pedido, establecer la fecha actual para evitar violación NOT NULL
        if (pedido.getFecha_pedido() == null) {
            pedido.setFecha_pedido(LocalDateTime.now());
        }
        return pedidoRepository.save(pedido);
    }
}
