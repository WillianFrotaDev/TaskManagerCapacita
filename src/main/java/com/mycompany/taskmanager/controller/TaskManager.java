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
    public <T extends Tarefa> void adicionarTarefa(ListaDeTarefas<T> listinha, T tarefinha){
        listinha.adicionar(tarefinha);
    }
    public void listarTarefas(ListaDeTarefas<Tarefa> tarefinhas, ListaDeTarefas<TarefaPrioritaria> tarefinhasPrio){
        for(int i = 0; i < tarefinhasPrio.tamanhoLista(); i++){
            TarefaPrioritaria tarefaPrio = tarefinhasPrio.obter(i);
            System.out.println((i+1) + "- " + tarefaPrio.getTitulo() + " | Descrição: " + tarefaPrio.getDescricao() + " | Status:" + (tarefaPrio.getConcluida() ? "✔" : "❌X"));
        }
        for(int i = 0; i < tarefinhas.tamanhoLista(); i++){
            Tarefa tare = tarefinhas.obter(i);
            System.out.println((i+ tarefinhasPrio.tamanhoLista() + 1) + "- " + tare.getTitulo() + " | Descrição: " + tare.getDescricao() + " | Status:" + (tare.getConcluida() ? "✔" : "❌X"));
        }
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
