/*Autora: Antonella Alares*/
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

/**
 *
 * @author anto_
 */
@Entity
public class Administradores {
    @Id
    @Column(name = "id_administrador")
     private int id;
     
     public Administradores (){
    
    }
     
     //GETTERS

    public int getId() {
        return id;
    }
    
    //SETTERS
    public void setId(int id) {
        this.id = id;
    }
     
     
     
}
