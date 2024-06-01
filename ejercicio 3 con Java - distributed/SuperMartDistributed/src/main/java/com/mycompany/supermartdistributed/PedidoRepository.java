/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermartdistributed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 *
 * @author anto_
 */
public interface PedidoRepository extends JpaRepository<Pedidos, Integer> {
    /* findByIdUsuario: Define la consulta utilizando la convención de nombres de Spring Data JPA, 
    que generará automáticamente una consulta basada en el campo idUsuario de la entidad Pedidos*/
    /*esto sería para poder coger este método desde el put de PedidoController (el put para ver un pedido de un usuario en concreto) */
     List<Pedidos> findByIdUsuario(Integer idUsuario);  /*Cuidado! idUsuario ha de ser el mismo nombre que el atributo de esta clase de Pedidos en Java! - Esta línea de código sería equivalente a: SELECT * FROM Pedidos p WHERE p.idUsuario = :idUsuario*/
     List<Pedidos> findByCantidadPedido(Integer cantidadPedido); /*CTRL +espacio al lado de List<Pedidos> para que te aparezca el listado de funciones disponibles*/
     
     @Query(value = "SELECT sum(t1.precio_total) as precio_final, t1.id_pedido from( SELECT ((ta.cantidad*tb.precio_producto)-(ta.cantidad*tb.precio_producto*tc.precioDescuento_promocion)) as precio_total, ta.id_pedido from producto_promocion_pedido ta inner join productos tb on ta.id_producto = tb.id_producto inner join promociones tc on ta.id_promocion = tc.id_promocion where id_pedido = :id_pedido) as t1 group by id_pedido",
             nativeQuery = true)
             Double calcularPrecioPedido(@Param("id_pedido") int id_pedido);
     }

