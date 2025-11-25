package com.huerto_hogar_e3.pedidos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.huerto_hogar_e3.pedidos.dto.UsuarioDTO;


@FeignClient(name = "usuarios",url = "http://52.1.232.64:8089/api/v1/usuario")
public interface UsuarioCliente {
    
    @GetMapping("/listar")
    public List<UsuarioDTO> obtenerUsuarios();

    @GetMapping("/{id}")
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") Integer id);
}
