/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.supermartdistributed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Administrador
 */

@Repository
public interface ProductoPromocionPedidoRepository extends JpaRepository<ProductoPromocionPedido, ProductoPromocionPedidoId> {

    @Query("SELECT SUM(p.cantidad) FROM ProductoPromocionPedido p WHERE p.idPedido = :idPedido")
    Integer calcularCantidadPedido(@Param("idPedido") int idPedido);
    
}