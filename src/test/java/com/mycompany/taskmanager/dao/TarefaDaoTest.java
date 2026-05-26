/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import com.mycompany.taskmanager.model.Tarefa;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author willianfrota
 */
public class TarefaDaoTest {
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

    @AfterEach
    void fecharConexao() throws SQLException {
        conexao.close();
    }

    @Test
    void deveSalvarTarefaNoBanco() throws SQLException {
        Tarefa tarefa = new Tarefa("Estudar DAO", "Testar com SQLite");

        tarefaDao.salvar(tarefa);

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tarefas")) {

            assertTrue(rs.next());
            assertEquals("Estudar DAO", rs.getString("titulo"));
            assertEquals("Testar com SQLite", rs.getString("descricao"));
            assertFalse(rs.getBoolean("concluida"));
            assertFalse(rs.getBoolean("prioritaria"));
        }
    }
}
