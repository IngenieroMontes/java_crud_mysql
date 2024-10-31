/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_crud_mysql;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class Conection {
    Connection connection = null;
    String user = "root";
    String password = "Pswadm_2024*";
    String nameDB = "schooldb";
    String host = "localhost";
    String port = "3306";
    
    String connectionPath = "jdbc:mysql://"+host+":"+port+"/"+nameDB;
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionPath,user,password);
            System.out.print("La conexión se ha realizado con éxito");
        }catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al conectar en la base de datos: "+e.toString());
        }
        
        return connection;
    }
}
