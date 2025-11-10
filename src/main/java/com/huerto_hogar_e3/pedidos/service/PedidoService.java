package com.huerto_hogar_e3.pedidos.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.huerto_hogar_e3.pedidos.client.UsuarioCliente;
import com.huerto_hogar_e3.pedidos.dto.UsuarioDTO;
import com.huerto_hogar_e3.pedidos.model.Pedido;
import com.huerto_hogar_e3.pedidos.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioCliente usuarioCliente;

    // listar pedidos
    public List<Pedido> pedidos(){
        return pedidoRepository.listaPedidos();
    }

   


    // para dto

    public PedidoService(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    // validar si usuarios existen en el servicio de usuarios
    public boolean validarUsuariosExistentes(List<Integer> usuarioIds) {
        List<UsuarioDTO> usuarios = obtenerTodosLosUsuarios();
        for (Integer id : usuarioIds) {
            boolean exists = usuarios.stream()
                    .anyMatch(u -> Objects.equals(u.getId_usuario(), id));
            if (!exists) {
                return false;
            }
        }
        return true;
    }


    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        try {
            List<UsuarioDTO> usuarios = usuarioCliente.obtenerUsuarios();
            return usuarios != null ? usuarios : Collections.emptyList();
        } catch (feign.FeignException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    // obtener usuarioDto por id (busca en la lista recuperada del servicio de usuarios)
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        if (id == null) return null;
        List<UsuarioDTO> usuarios = obtenerTodosLosUsuarios();
        return usuarios.stream()
                .filter(u -> Objects.equals(u.getId_usuario(), id))
                .findFirst()
                .orElse(null);
    }
   
     public Pedido agregarPedido(Pedido pedido){
        // Si no llega fecha_pedido, establecer la fecha actual para evitar violación NOT NULL
        if (pedido.getFecha_pedido() == null) {
            pedido.setFecha_pedido(LocalDateTime.now());
        }
           // validar si existe el id de usuario
           if (pedido.getUsuarioId() == null) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario.id es requerido");
           }
           Integer usuarioId = pedido.getUsuarioId();
           UsuarioDTO usuario = obtenerUsuarioPorId(usuarioId);
           if (usuario == null) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario con id " + usuarioId + " no existe");
           }

           // Guardamos solo el id en la entidad; si necesitas datos del usuario los puedes usar en la lógica
           pedido.setUsuarioId(usuario.getId_usuario());
        return pedidoRepository.save(pedido);
    }
}