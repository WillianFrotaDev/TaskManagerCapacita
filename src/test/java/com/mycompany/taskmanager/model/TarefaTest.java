/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import org.junit.jupiter.api.Test;
/**
 *
 * @author willianfrota
 */
public class TarefaTest {
    @Test
    void deveCriarTarefaComTituloEDescricao() {
        Tarefa tarefa = new Tarefa("Estudar Java", "Estudar JUnit");

        assertEquals("Estudar Java", tarefa.getTitulo());
        assertEquals("Estudar JUnit", tarefa.getDescricao());
        assertFalse(tarefa.getConcluida());
    }
    
}
