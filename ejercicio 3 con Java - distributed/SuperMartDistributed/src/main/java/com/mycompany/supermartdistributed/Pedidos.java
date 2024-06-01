/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermartdistributed;



import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.*;
import javax.persistence.*;

/**
 *
 * @author anto_
 */

@Entity
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_pedido")
    private int idPedido;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "id_tarjeta")
    private int idTarjeta;
    @Column(name = "cantidad_pedido")
    private int cantidadPedido;
    @Column(name = "precioTotal_pedido")
    private double precioTotalPedido;
    @Column(name = "estadoProcesando_pedido")
    private Timestamp estadoProcesando;
    @Column(name = "estadoEnviado_pedido")
    private Timestamp estadoEnviado;
    @Column(name = "estadoRecibido_pedido")
    private Timestamp estadoRecibido;
    @Column(name = "estadoCancelado_pedido")
    private Timestamp estadoCancelado;
    @Column(name = "fecha_pedido")
    private Date fechaPedido;
    @Column(name = "fechaEntrega_pedido")
    private Date fechaEntrega;

    public Pedidos() {
    }
    
    //GETTERS
    
    public int getIdPedido() {
        return idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public int getCantidadPedido() {
        return cantidadPedido;
    }



    public double getPrecioTotalPedido() {
        return precioTotalPedido;
    }

    public Timestamp getEstadoProcesando() {
        return estadoProcesando;
    }

    public Timestamp getEstadoEnviado() {
        return estadoEnviado;
    }

    public Timestamp getEstadoRecibido() {
        return estadoRecibido;
    }

    public Timestamp getEstadoCancelado() {
        return estadoCancelado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }
    
    
    //SETTERS
    
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public void setCantidadPedido(int cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }




    public void setPrecioTotalPedido(double precioTotalPedido) {
        this.precioTotalPedido = precioTotalPedido;
    }

    public void setEstadoProcesando(Timestamp estadoProcesando) {
        this.estadoProcesando = estadoProcesando;
    }

    public void setEstadoEnviado(Timestamp estadoEnviado) {
        this.estadoEnviado = estadoEnviado;
    }

    public void setEstadoRecibido(Timestamp estadoRecibido) {
        this.estadoRecibido = estadoRecibido;
    }

    public void setEstadoCancelado(Timestamp estadoCancelado) {
        this.estadoCancelado = estadoCancelado;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
    /*MÉTODOS DAO PARA ADAPTAR*/
    
    /*public Timestamp getEstadoEnviado(){
    return PedidoDAO.getEstadoEnviado(this.idPedido);
    }
    
    public Timestamp getEstadoRecibido(){
    return PedidoDAO.getEstadoRecibido(this.idPedido);
    }
    
    
    public static void anadirProductoPedido(int id_pedido, int id_promocion, int id_producto, int cantidad){
        PedidoDAO.anadirProcuctoPedido(id_pedido, id_promocion, id_producto, cantidad);
        System.out.println("Producto con id: " + id_producto + " añadido al pedido con id: " + id_pedido + " con la promoción id: " + id_promocion + " con la cantidad de " + cantidad + " productos.");
        
    }
    
    
    public static int calcularCantidadPedido(int id_pedido){
        return PedidoDAO.calcularCantidadPedido(id_pedido);
    }
    
    public static double calcularPrecioPedido(int id_pedido){
        return PedidoDAO.calcularPrecioPedido(id_pedido);

    }
    
    
    public static void procesarPedido(int id_pedido){
        PedidoDAO.procesarPedido(id_pedido);
    }
    
    
    public static void confirmarEnviado(int id_pedido){
    PedidoDAO.confirmarEnviado(id_pedido);
    }
      
    
    public static void confirmarEntrega(int id_pedido){
    PedidoDAO.confirmarEntrega(id_pedido);
    }
    
  
    public static void cancelarPedido(int id_pedido){
    PedidoDAO.cancelarPedido(id_pedido);
    }*/

    

    
}

