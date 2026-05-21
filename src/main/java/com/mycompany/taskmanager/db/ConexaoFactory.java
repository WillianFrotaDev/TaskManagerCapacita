/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author willianfrota
 */
public class ConexaoFactory { // essa classe é necessaria pois facilita a conexao ao banco de dados pois ela ja guarda as credenciais de acesso
    
    private static final String URL = "jdbc:mysql://localhost:3306/taskManagerDB";// "jdbc:mysql://" ele tem o papel de usar o jdbc para o mysql
    //localhost: determina que o host do banco de dados, é o meu proprio computador
    //3306 é a porta padrao que o mysql usa para se conectar
    //taskManagerDB é o nome do banco de dados
    
    // Essas sao as credenciais do banco
    private static final String USUARIO = "root";
    
    private static final String SENHA = "123456";
    
    
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,SENHA);// toda vez que eu quisse me conectar com banco de dados teria que usar esse comando junto com as credenciais
    }// agora com o metodo conectar nao vai ser preciso eu colocar as credencias toda hora
}
