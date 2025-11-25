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

import com.huerto_hogar_e3.pedidos.dto.ProductoDTO;
import com.huerto_hogar_e3.pedidos.dto.UsuarioDTO;
import com.huerto_hogar_e3.pedidos.model.Pedido;
import com.huerto_hogar_e3.pedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/pedido")
@Tag(name = "pedidos",description = "controlador para gestionar pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    // listar pedidos
    @GetMapping("/listar")
    @Operation(summary = "Lista de Pedidos",description = "Obtiene una lista de pedidos realizados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Lista todos los pedidos correctamente",
        content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Pedido.class)))),
        @ApiResponse(responseCode = "204", description = "No se encontraron pedidos"),
        @ApiResponse(responseCode = "500",description = "Error interno del servidor")
    })
    public ResponseEntity<List<Pedido>> listar(){
        try {
            List<Pedido> pedidos = pedidoService.pedidos();
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // guardar nuevo pedidos
    @PostMapping("/guardar")
    @Operation(summary = "Crear Pedido", description = "Crea un nuevo pedido validando que el usuario y producto existan")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Usuario o producto no existe, o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido){
        Pedido nuevPedido = pedidoService.agregarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevPedido);
    }


    // Listar usuriosDTO
    @GetMapping("/usuarios")
    @Operation(summary = "Listar Usuarios", description = "Obtiene la lista de usuarios disponibles desde el servicio externo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),
        @ApiResponse(responseCode = "503", description = "Servicio de usuarios no disponible")
    })
    public ResponseEntity<List<UsuarioDTO>> obtenerLosUsuarios(){
        List<UsuarioDTO> usuarios = pedidoService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    //Listar productosDTO
    @GetMapping("/productos")
    @Operation(summary = "Listar Productos", description = "Obtiene la lista de productos disponibles desde el servicio externo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductoDTO.class)))),
        @ApiResponse(responseCode = "503", description = "Servicio de productos no disponible")
    })
    public ResponseEntity<List<ProductoDTO>> obtenerProductos(){
        List<ProductoDTO> productos = pedidoService.obtenerTodosLosProductos();

        return ResponseEntity.ok().body(productos);
    }
    
}
