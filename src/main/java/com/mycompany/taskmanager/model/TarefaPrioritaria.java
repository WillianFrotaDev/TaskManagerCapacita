/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author willianfrota
 */
@Entity
@Table(name = "tarefas_prioritarias")
public class TarefaPrioritaria extends Tarefa{
    
    //Construtor padrao exigido pelo Hibernate
    public TarefaPrioritaria(){
        super();
    }
    
    // Atributos herdados da classe tarefa OBS: o hibernate primeiro cria um objeto java para depois determinar seus atributos logo nao da para buscar da classe mae tarefa
    // Aqui temos uma herança automatica de atributos e o hibernate gerencia a chave estrangeira e primaria dessa classe para ser possivel buscar com o JOIN
    public TarefaPrioritaria(String titulo, String descricao, Usuario usuario){
        super(titulo,descricao,usuario);
    }
    @Override
    public String getTitulo(){
        return super.getTitulo();// detalhe sem esse super ele nao busca da classe mae Tarefa
    }
    @Override
    public boolean getPrioridade(){
        return true;
    }
}
