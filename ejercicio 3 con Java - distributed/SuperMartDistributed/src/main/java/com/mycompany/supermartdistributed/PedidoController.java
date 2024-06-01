/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermartdistributed;

// Importa las anotaciones y clases necesarias de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



/**
 *
 * @author anto_
 */
// Marca la clase como un controlador REST, lo que significa que manejará las solicitudes HTTP
@RestController
// Define la ruta base para las solicitudes HTTP manejadas por este controlador
@RequestMapping("/pedidos")
public class PedidoController {
    
    // Inyecta una instancia de PedidoRepository, que proporciona métodos para interactuar con la base de datos
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProductoPromocionPedidoRepository productoPromocionPedidoRepository;
    
    // Maneja las solicitudes GET a /pedidos para obtener una lista de todas los pedidos
    @GetMapping
    public List<Pedidos> getAllPedidos() {
        // Llama al método findAll() del repositorio para obtener todas los pedidos
        return pedidoRepository.findAll();
    }

    // Maneja las solicitudes GET a /pedidos/{id} para obtener un pedido específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable int id) { // ResponseEntity es una clase de Spring que representa toda la respuesta HTTP: el estado, los encabezados y el cuerpo
        
        // Busca un pedido por su ID
        Optional<Pedidos> pedido = pedidoRepository.findById(id);
        // Si se encuentra el pedido, devuelve una respuesta HTTP 200 (OK) con el pedido encontrado
        if (pedido.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            return ResponseEntity.ok(pedido.get());  
         // El método ok es un método estático de ResponseEntity que crea una instancia de ResponseEntity con un estado HTTP 200 (OK) y el cuerpo de la respuesta configurado con el valor proporcionado
        // Si el Optional contiene un valor (es decir, no está vacío), get() devolverá ese valor
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("idUsuario/{idUsuario}")
    public ResponseEntity<List<Pedidos>> getPedidoByIdUsuario(@PathVariable int idUsuario){
        List<Pedidos> pedido = pedidoRepository.findByIdUsuario(idUsuario);
        
        if(pedido.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(pedido);
        }
    }
    
    @GetMapping("cantidades/{cantidad2}")
    public ResponseEntity<List<Pedidos>> getPedidoByCantidad(@PathVariable int cantidad2){
        List<Pedidos> pedido = pedidoRepository.findByCantidadPedido(cantidad2);
        
        if(pedido.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(pedido);
        }
    }
    
    @GetMapping("calcular-cantidad/{id_pedido}")
    public ResponseEntity<Integer> calcularCantidadPedido(@PathVariable int id_pedido){
        Integer cantidad = productoPromocionPedidoRepository.calcularCantidadPedido(id_pedido);
        
        if(cantidad != null){
            return ResponseEntity.ok(cantidad);
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    // Maneja las solicitudes POST a /pedidos para crear un nuevo pedido
    @PostMapping
    public Pedidos createPedido(@RequestBody Pedidos pedido) {
         // Guarda el nuevo pedido en la base de datos y lo devuelve en la respuesta
        return pedidoRepository.save(pedido);
    }

    // Maneja las solicitudes PUT a /pedidos/{id} para actualizar un pedido existente
    @PutMapping("actualizar-fechas/{id}")
    public ResponseEntity<Pedidos> updatePedido(@PathVariable int id, @RequestBody Pedidos pedidoDetails) {
        // Busca un pedido por su ID
        Optional<Pedidos> pedido = pedidoRepository.findById(id);
        // Si se encuentra el pedido, actualiza sus detalles y lo guarda
        if (pedido.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            Pedidos pedidoToUpdate = pedido.get();
            // Actualiza el idTarjeta y el idUsuario del pedido con los detalles proporcionados en la solicitud
            pedidoToUpdate.setFechaPedido(pedidoDetails. getFechaPedido());
            pedidoToUpdate.setFechaEntrega(pedidoDetails.getFechaEntrega());
            // Guarda el pedido actualizado y devuelve una respuesta HTTP 200 (OK)
            return ResponseEntity.ok(pedidoRepository.save(pedidoToUpdate));
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/actualizar-cantidad/{idPedido}")
    public ResponseEntity<Pedidos> actualizarCantidadPedido(@PathVariable int idPedido) {
        Integer nuevaCantidad = productoPromocionPedidoRepository.calcularCantidadPedido(idPedido);
        if (nuevaCantidad != null) {
            Pedidos pedidoToUpdate = pedidoRepository.findById(idPedido).orElse(null);
            if (pedidoToUpdate != null) {
                pedidoToUpdate.setCantidadPedido(nuevaCantidad);
                return ResponseEntity.ok(pedidoRepository.save(pedidoToUpdate));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Maneja las solicitudes DELETE a /pedidos/{id} para eliminar un pedido por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
         // Maneja las solicitudes DELETE a /pedidos/{id} para eliminar un pedido por su ID
        Optional<Pedidos> pedido = pedidoRepository.findById(id);
        // Si se encuentra el pedido, la elimina de la base de datos
        if (pedido.isPresent()) { //isPresent(): Devuelve true si el Optional contiene un valor, o false si está vacío.
            pedidoRepository.delete(pedido.get());
            // Devuelve una respuesta HTTP 200 (OK) sin contenido
            return ResponseEntity.ok().build();
            //build() se utiliza para construir una instancia de ResponseEntity sin cuerpo(body)
        } else {
            // Si no se encuentra el pedido, devuelve una respuesta HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
    /*cuidado porque al actualizar-precio desde Postman, este pedido debe estar también puesto en la tabla producto_promocion_pedido,
    para que pueda coger los productos y el descuento de la promoción correspondiente en la query de calcularPrecioPedido del Repository*/
    @PutMapping("/actualizar-precio/{id_pedido}")
    public ResponseEntity<Pedidos> calcularPrecioPedido(@PathVariable int id_pedido){  /* esto va fuera! @RequestBody Pedidos pedidoDetails*/

        Double precio = pedidoRepository.calcularPrecioPedido(id_pedido);
         if (precio != null) {
            Pedidos pedidoToUpdate = pedidoRepository.findById(id_pedido).orElse(null);
            if (pedidoToUpdate != null) {
                pedidoToUpdate.setPrecioTotalPedido(precio);
                return ResponseEntity.ok(pedidoRepository.save(pedidoToUpdate));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
