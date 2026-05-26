/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

/**
 *
 * @author willianfrota
 */
public class Tarefa {
    
    private int id;
    private String titulo;
    private String descricao;
    private boolean concluido;
    
    public Tarefa(String titulo, String descricao){
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = false;
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
