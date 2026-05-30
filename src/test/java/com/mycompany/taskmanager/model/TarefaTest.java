/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import com.mycompany.taskmanager.dao.TarefaDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    void prepararBanco() throws SQLException {// cria o banco de dados para fazer os testes
        conexao = DriverManager.getConnection("jdbc:sqlite::memory:");
        tarefaDao = new TarefaDAO(conexao);
        String sql = """
                CREATE TABLE tarefas (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    descricao TEXT,
                    concluida BOOLEAN,
                    prioritaria BOOLEAN
                )
            """;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {// 
            stmt.execute();
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

        assertTrue(tarefa.getConcluida());// verifica se tarefa realmente esta concluida
    }
    
    @Test
    void deveRemoverTarefa() throws SQLException {
        tarefaDao = new TarefaDAO(conexao);
        Tarefa tarefa = new Tarefa("Teste", "DAO");

        tarefaDao.salvar(tarefa);

        tarefaDao.remover(1);// eh o primeiro do banco de dados
        String sql = "SELECT * FROM tarefas" ;
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultadoConsulta = stmt.executeQuery()) {

            assertFalse(resultadoConsulta.next());
        }
    }
}
