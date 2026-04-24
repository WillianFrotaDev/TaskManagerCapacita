/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import java.util.ArrayList;
/**
 *
 * @author willianfrota
 */
public class TaskManager {
    ArrayList<Tarefa> tarefinhas;
    
    public TaskManager(){
         tarefinhas = new ArrayList<>();
    }
    public void adicionarTarefa(String titulo, String descricao, boolean prioridade){
        if(prioridade){
            Tarefa p = new TarefaPrioritaria(titulo, descricao);
            tarefinhas.add(0, p);
        } else{
            Tarefa t = new Tarefa(titulo, descricao);
            tarefinhas.add(t);
        }
    }
    public void listarTarefas(){
        for(int i = 0; i < tarefinhas.size(); i++){
            Tarefa tari = tarefinhas.get(i);
            System.out.println((i+1) + "- " + tari.getTitulo() + " | Descrição: " + tari.getDescricao() + " | Status:" + (tari.getConcluida() ? "✔" : "❌"));
        }
    }
    public void removerTarefa(int i){// vai uma excecao
        tarefinhas.remove(i - 1);
    }
    public void concluirTarefa(int i){ // vai uma excecao
        Tarefa ti = tarefinhas.get(i -1);
        if(ti.concluir()){
            System.out.println("Tarefa concluida");
        } else{
            System.out.println("Esta tarefa ja esta concluida");
        }
        
        
    }
}
