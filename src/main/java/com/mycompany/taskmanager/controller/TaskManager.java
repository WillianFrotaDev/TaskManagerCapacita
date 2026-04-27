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
    public <T extends Tarefa> void adicionarTarefa(Lista<T> listinha, T tarefinha){
        listinha.adicionar(tarefinha);
    }
    public void listarTarefas(Lista<Tarefa> tarefinhas, Lista<TarefaPrioritaria> tarefinhasPrio){
        for(int i = 0; i < tarefinhasPrio.tamanhoLista(); i++){
            TarefaPrioritaria tarefaPrio = tarefinhasPrio.obter(i);
            System.out.println((i+1) + "- " + tarefaPrio.getTitulo() + " | Descrição: " + tarefaPrio.getDescricao() + " | Status:" + (tarefaPrio.getConcluida() ? "✔" : "❌X"));
        }
        for(int i = 0; i < tarefinhas.tamanhoLista(); i++){
            Tarefa tare = tarefinhas.obter(i);
            System.out.println((i+ tarefinhasPrio.tamanhoLista() + 1) + "- " + tare.getTitulo() + " | Descrição: " + tare.getDescricao() + " | Status:" + (tare.getConcluida() ? "✔" : "❌X"));
        }
    }
    public void removerTarefa(Lista<Tarefa> tarefinhas, Lista<TarefaPrioritaria> tarefinhasPrio, int indice){// vai uma excecao
        if(indice <= tarefinhasPrio.tamanhoLista()){
            tarefinhasPrio.remover(indice);
        } else if(indice > tarefinhasPrio.tamanhoLista()){
            tarefinhas.remover(indice - tarefinhasPrio.tamanhoLista());
        }
        
        tarefinhas.remover(indice - 1);
    }
    public void concluirTarefa(Lista<Tarefa> tarefinhas, Lista<TarefaPrioritaria> tarefinhasPrio,int indice){ // vai uma excecao
        if(indice <= tarefinhasPrio.tamanhoLista()){
            TarefaPrioritaria tarePrio = tarefinhasPrio.obter(indice);
            tarePrio.concluir();
        } else if(indice > tarefinhasPrio.tamanhoLista()){
            Tarefa tare = tarefinhas.obter(indice - tarefinhasPrio.tamanhoLista());
            tare.concluir();
        }
        
        
    }
}
