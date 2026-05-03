/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author willianfrota
 */
public class TaskManager {
    
    
    public TaskManager(){
         
    }
    public <T extends Tarefa> void adicionarTarefa(ListaDeTarefas<? super Tarefa> listinha, T tarefinha){
        listinha.adicionar(tarefinha);
    }
    
    public void listarTarefas(ListaDeTarefas<? extends Tarefa> tarefinhas, ListaDeTarefas<? extends Tarefa> tarefinhasPrio){
        int start = 1;
        int index = tarefinhasPrio.listar(start);
        
    }
    public void removerTarefa(ListaDeTarefas<Tarefa> tarefinhas, ListaDeTarefas<TarefaPrioritaria> tarefinhasPrio, int indice){// vai uma excecao
        if(indice <= tarefinhasPrio.tamanhoLista()){
            tarefinhasPrio.remover(indice -1);
        } else if(indice > tarefinhasPrio.tamanhoLista()){
            tarefinhas.remover(indice - tarefinhasPrio.tamanhoLista() - 1);
        }
        
        
    }
    public void concluirTarefa(ListaDeTarefas<Tarefa> tarefinhas, ListaDeTarefas<TarefaPrioritaria> tarefinhasPrio,int indice){ // vai uma excecao
        if(indice <= tarefinhasPrio.tamanhoLista()){
            TarefaPrioritaria tarePrio = tarefinhasPrio.obter(indice -1);
            tarePrio.concluir();
        } else if(indice > tarefinhasPrio.tamanhoLista()){
            Tarefa tare = tarefinhas.obter(indice - tarefinhasPrio.tamanhoLista() - 1);
            tare.concluir();
        }
        
        
    }
}
