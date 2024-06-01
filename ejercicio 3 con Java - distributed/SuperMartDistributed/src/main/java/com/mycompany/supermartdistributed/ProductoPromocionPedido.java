/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermartdistributed;


import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Administrador
 */
/*entidad ProductoPromocionPedido que tiene una clave primaria compuesta por idProducto, idPromocion y idPedido. 
Primero, necesitas definir una clase auxiliar que represente la clave primaria compuesta. 
Luego, se utiliza @IdClass en la entidad para indicar que esta entidad tiene una clave primaria compuesta definida por esta clase auxiliar.*/

@Entity
@Table(name = "producto_promocion_pedido")
@IdClass(ProductoPromocionPedidoId.class) //Indica que esta entidad tiene una clave primaria compuesta y que la clase ProductoPromocionPedidoId define esta clave
public class ProductoPromocionPedido  implements Serializable {

 
    @Id
    @Column(name = "id_producto")
    private int idProducto;

    @Id
    @Column(name = "id_promocion")
    private int idPromocion;

    @Id
    @Column(name = "id_pedido")
    private int idPedido;

    private int cantidad;

    // Constructor por defecto
    public ProductoPromocionPedido() {
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}