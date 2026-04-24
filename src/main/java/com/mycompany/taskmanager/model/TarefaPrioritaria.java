/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

/**
 *
 * @author willianfrota
 */
public class TarefaPrioritaria extends Tarefa{
    public TarefaPrioritaria(String titulo, String descricao){
        super(titulo,descricao);
    }
    @Override
    public String getTitulo(){
        return "| PRIORIDADE " + super.getTitulo();// detalhe sem esse super ele nao busca da classe mae Tarefa
    }
}
