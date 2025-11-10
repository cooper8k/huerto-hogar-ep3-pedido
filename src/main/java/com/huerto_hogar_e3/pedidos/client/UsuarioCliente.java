package com.huerto_hogar_e3.pedidos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.huerto_hogar_e3.pedidos.dto.UsuarioDTO;


@FeignClient(name = "usuarios",url = "http://localhost:8090/api/v1/usuario")
public interface UsuarioCliente {
    
    @GetMapping("/listar")
    public List<UsuarioDTO> obtenerUsuarios();
}
