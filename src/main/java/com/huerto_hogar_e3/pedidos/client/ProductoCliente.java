package com.huerto_hogar_e3.pedidos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.huerto_hogar_e3.pedidos.dto.ProductoDTO;

@FeignClient(name = "productos",url = "http://localhost:8080/api/v1/huertohogar/productos")
public interface ProductoCliente {

    @GetMapping("/listar")
    public List<ProductoDTO>obtenerProductos();

}
