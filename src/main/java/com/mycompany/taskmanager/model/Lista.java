/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

/**
 *
 * @author willianfrota
 */
public interface Lista<T> {
    void adicionar(T item);

    T obter(int indice);

    void remover(int indice);

    int tamanhoLista();

    
}
