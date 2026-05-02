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
    public void listar(){
        for(Tarefa tf : minhaLista){
            System.out.println(tf);
        }    
    }
    public void remover(int indice){
        minhaLista.remove(indice);
    }
    public int tamanhoLista(){
        return minhaLista.size();
    
    }
    
}
