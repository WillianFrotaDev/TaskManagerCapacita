/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void deveConcluirTarefa() {
        Tarefa tarefa = new Tarefa("Estudar", "JUnit");

        tarefa.concluir();

        assertTrue(tarefa.getConcluida());
    }
    @Test
    void naoDeveCriarTarefaComTituloVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tarefa("", "Descrição qualquer");
        });
    }
}
