/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willianfrota
 */
@Entity // determina a classe como entidade para que possa ir pro banco
@Table(name = "usuarios") // determina qual tabela do banco essa classe vai
public class Usuario {
    
    @Id // o ID de identificação la no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // aqui diz que o valor vai ser gerado automaticamente para ser uma chave primaria de identificaçao
    private int id;// chave primaria
    
    private String nome;
    private String senha;
    private String email;
    
    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas = new ArrayList<>();// isso serve para criar uma relacao usuario e tarefas
    
    
    public Usuario(){} // precisa por que ao consultar o banco o hibernate faz uma conversao das linhas da tabela com objetos java e ele usa esse construtor para isso
    
    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    
    
    
    //Tambem precisa de ter todos os getters e setters para poder pegar cada informacao de cada objeto criado pelo hibernate
    
    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
}
