/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import com.mycompany.taskmanager.dao.TarefaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author willianfrota
 */
public class TarefaTest {
    private Connection conexao;
    private TarefaDAO tarefaDao;

    @BeforeEach
    void prepararBanco() throws SQLException {
        conexao = DriverManager.getConnection("jdbc:sqlite::memory:");
        tarefaDao = new TarefaDAO(conexao);

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute("""
                CREATE TABLE tarefas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    descricao TEXT,
                    concluida BOOLEAN,
                    prioritaria BOOLEAN
                )
            """);
        }
    }
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
    @Test
    void naoDeveCriarTarefaComTituloNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tarefa(null, "Descrição qualquer");
        });
    }

    @Test
    void naoDeveCriarTarefaComTituloSoComEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tarefa("   ", "Descrição qualquer");
        });
    }
    @Test
    void deveCriarTarefaPrioritaria() {
        TarefaPrioritaria tarefa =
                new TarefaPrioritaria("Urgente", "Muito urgente");

        assertEquals("Urgente", tarefa.getTitulo());
    }
    @Test
    void deveRemoverTarefa() throws SQLException {
        tarefaDao = new TarefaDAO(conexao);
        Tarefa tarefa = new Tarefa("Teste", "DAO");

        tarefaDao.salvar(tarefa);

        tarefaDao.remover(1);

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tarefas")) {

            assertFalse(rs.next());
        }
    }
}
