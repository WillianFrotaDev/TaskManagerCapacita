/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import jakarta.persistence.*;
/**
 *
 * @author willianfrota
 */
@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// chave primaria da tabela tarefas
    private int id;
    
    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;
    
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    
    
    private boolean concluido;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)// cria a chave estrangeira
    private Usuario usuario;// isso serve para criar uma relacao tarefa e usuario
    
    public Tarefa(){}
    
    public Tarefa(String titulo, String descricao){
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
    }
    
    public int getId(){
        return id;
    }
    
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public boolean getConcluida(){
        return concluido;
    }
    public boolean getPrioridade(){
        return false;
    }
    
    public boolean concluir(){ // uma parada que eu nao sabia quando é um metodo booleano precisa returnar true ou false
        if(this.concluido){
            return false;
        } 
        this.concluido = true;
        return true;
    }
    public int getId() {// id para o banco de dados buscar
        return id;
    }

    public void setId(int id) {// para o banco de dados determina o id
        this.id = id;
    }

}
