/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author willianfrota
 */
public class ConexaoFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/taskManagerDB";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";
    
    
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,SENHA);
    }
}
