/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import org.junit.jupiter.api.*;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author willianfrota
 */
public class TarefaDaoTest {
    private Connection conexao;
    private TarefaDAO tarefaDao;

    @BeforeEach
    void prepararBanco() throws SQLException {// cria o banco de dados com os recursos e id
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
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.execute();// para criar o banco de dados
        }
    }

    @AfterEach // depois do teste tem que fechar a conexao
    void fecharConexao() throws SQLException {
        conexao.close();
    }

    @Test
    void deveSalvarTarefaNoBanco() throws SQLException {
        Tarefa tarefa = new Tarefa("Testes com SQLite", "testar em outro banco de dados para nao dar problema");

        tarefaDao.salvar(tarefa);
        String sql = "SELECT * FROM tarefas";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultadoSalvar = stmt.executeQuery()) {// pega o resultado da consulta

            assertTrue(resultadoSalvar.next());// move o cursor para a proxima linha e retorna true se essa linha existe ou false se nao existe
            // vai servir para ver se foi criado alguma coisa no banco de dados, afinal o banco de dados eh novo e nao tem tarefas anteriores
            assertEquals("Testes com SQLite", resultadoSalvar.getString("titulo"));// o teste so passa se
            assertEquals("testar em outro banco de dados para nao dar problema", resultadoSalvar.getString("descricao"));
            assertFalse(resultadoSalvar.getBoolean("concluida"));
            assertFalse(resultadoSalvar.getBoolean("prioritaria"));
        }
    }
    @Test
    void deveSalvarTarefaPrioritariaNoBanco() throws SQLException{
        
        TarefaPrioritaria tarefaPrio = new TarefaPrioritaria("Salva Prio", "Tem que salvar");
        tarefaDao.salvar(tarefaPrio);
        
        String sql = "SELECT * FROM tarefas";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultadoSalvar = stmt.executeQuery()) {// pega o resultado da consulta

            assertTrue(resultadoSalvar.next());// verifica se a tarefa foi criada
            assertEquals("Salva Prio", resultadoSalvar.getString("titulo"));// o teste so passa se
            assertEquals("Tem que salvar", resultadoSalvar.getString("descricao"));
            assertFalse(resultadoSalvar.getBoolean("concluida"));
            assertTrue(resultadoSalvar.getBoolean("prioritaria"));// tem que ser true, porque eh prioritaria
        }
    }
}
