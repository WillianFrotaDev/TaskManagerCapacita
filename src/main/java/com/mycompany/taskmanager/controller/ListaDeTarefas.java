/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.model.Lista;
import com.mycompany.taskmanager.model.Tarefa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willianfrota
 */
public class ListaDeTarefas<T extends Tarefa> implements Lista<T> {
    List<T> minhaLista = new ArrayList<>();
    
    public void adicionar(T item){
        minhaLista.add(item);
    } 
    public T obter(int indice){
        return minhaLista.get(indice);
        
    }
    public int listar(int start){
        
        for(int i = 0; i < minhaLista.size(); i++){
            Tarefa t = minhaLista.get(i);
            System.out.println((i+1) + "- " + t.getTitulo() + " | Descrição: " + t.getDescricao() + " | Status:" + (t.getConcluida() ? "✔" : "❌X"));
        }
       
        return start + minhaLista.size();
    }
    public void remover(int indice){
        minhaLista.remove(indice);
    }
    public int tamanhoLista(){
        return minhaLista.size();
    
    }
    
}
