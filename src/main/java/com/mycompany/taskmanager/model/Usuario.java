/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author willianfrota
 */
@Entity // determina a classe como entidade para que possa ir pro banco
@Table(name = "usuarios") // determina qual tabela do banco essa classe vai
public class Usuario {
    
    @Id // o ID de identificação la no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // chave primaria da tabela usuarios
    private int id;// chave primaria
    
    private String nome;
    private String senha;
    private String email;
    
    Usu
    
    
}
