/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import com.mycompany.taskmanager.controller.ListaDeTarefas;
import com.mycompany.taskmanager.db.ConexaoFactory;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author willianfrota
 */
public class TarefaDAO {
    
    
    public ListaDeTarefas<Tarefa> listar() throws SQLException{
        
        ListaDeTarefas<Tarefa> listinha = new ListaDeTarefas<>();
        String sql = "SELECT * FROM tarefas";
        try{
            
            Connection conexao = ConexaoFactory.conectar();
            PreparedStatement stm = conexao.prepareStatement(sql);// preparou o comando
            ResultSet resultadoConsulta = stm.executeQuery();// serve para pegar o resultado da consulta ao banco de dados
            
             while (resultadoConsulta.next()) {

                int id = resultadoConsulta.getInt("id");
                String titulo = resultadoConsulta.getString("titulo");
                String descricao = resultadoConsulta.getString("descricao");
                boolean concluida = resultadoConsulta.getBoolean("concluida");
                boolean prioritaria = resultadoConsulta.getBoolean("prioritaria");

                Tarefa tarefa;
                
                if (prioritaria) {
                tarefa = new TarefaPrioritaria(titulo, descricao);
                } else {
                    tarefa = new Tarefa(titulo, descricao);
                }

                tarefa.setId(id);

            if (concluida) {
                tarefa.concluir();
            }

            listinha.adicionar(tarefa);
        }
            
            resultadoConsulta.close();
            conexao.close();
            stm.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listinha;
    }
    
    public void salvar(Tarefa tarefa){
        
        String sql = "INSERT INTO tarefas (titulo, descricao, concluida, prioritaria) VALUES (?, ?, ?, ?)";
        try{
           Connection conexao = ConexaoFactory.conectar(); 
           PreparedStatement stm = conexao.prepareStatement(sql);// PreparedStatement serve para executar comandos SQL no banco de dados
           
           // isso representa a ordem de cada um dos elementos associado a string sql la de cima
           stm.setString(1, tarefa.getTitulo());
           stm.setString(2, tarefa.getDescricao());
           stm.setBoolean(3, tarefa.getConcluida());
           stm.setBoolean(4, tarefa.getPrioridade());
           
           stm.executeUpdate();// ele é usado para operacoes em sql, usado nas operacoes INSERT, UPDATE e DELETE
           //tambem usado na criacao de tabelas (CREATE TABLE) e em alteracoes estruturais (ALTER TABLE)
            
           conexao.close();
           stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void buscarPorId(int id) throws SQLException{
        
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Connection conexao = ConexaoFactory.conectar();
        PreparedStatement state = conexao.prepareStatement(sql);
        
        state.setInt(1, id);
        ResultSet resultadoConsulta = state.executeQuery();
        
        if (resulta.next()) {

            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");
            boolean prioritaria = rs.getBoolean("prioritaria");

            Tarefa tarefa;

            if (prioritaria) {
                tarefa = new TarefaPrioritaria(titulo, descricao);
            } else {
                tarefa = new Tarefa(titulo, descricao);
            }

            tarefa.setId(idBusca);

            return tarefa;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;

        
    }
    
    public void editar(int id){}
    
    public void remover(int id){}
    
    
}
