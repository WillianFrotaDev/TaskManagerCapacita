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
           
           //executeUpdate: altera dados
           //executeQuery: busca dados
           stm.executeUpdate();// ele é usado para operacoes em sql, usado nas operacoes INSERT, UPDATE e DELETE, 
           //tambem usado na criacao de tabelas (CREATE TABLE) e em alteracoes estruturais (ALTER TABLE)
            
           conexao.close();
           stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public Tarefa buscarPorId(int id) throws SQLException{
        
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        Connection conexao = ConexaoFactory.conectar();
        PreparedStatement state = conexao.prepareStatement(sql);
        
        try{
            state.setInt(1, id);
            ResultSet resultadoConsulta = state.executeQuery();// executeQuery serve para consultar os dados
        
            if (resultadoConsulta.next()) {

                // esses parametros desses metodos, é a categoria la da chave primaria
                String titulo = resultadoConsulta.getString("titulo");
                String descricao = resultadoConsulta.getString("descricao");
                boolean prioritaria = resultadoConsulta.getBoolean("prioritaria");
                boolean concluida = resultadoConsulta.getBoolean("concluida");

                
                Tarefa tarefa;

                if (prioritaria) {
                    tarefa = new TarefaPrioritaria(titulo, descricao);
                } else {
                    tarefa = new Tarefa(titulo, descricao);
                }
                
                if(concluida){
                    tarefa.concluir();
                }
                
                tarefa.setId(id);
                resultadoConsulta.close();
                return tarefa;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
    conexao.close();
    state.close();
    
    
    return null;// se ele nao encontrar ele retorna null
    
        
    }
    
    public void editar(Tarefa tarefa){
        
        // vou usar junto com buscarPorId
        String sql = """
        UPDATE tarefas
        SET titulo = ?, descricao = ?, concluida = ?, prioritaria = ?
        WHERE id = ?""";

        try (
            Connection conexao = ConexaoFactory.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setBoolean(3, tarefa.getConcluida());

            // verifica se eh prioritaria, para ja atribuir ao comando que sera executado
            stmt.setBoolean(4, tarefa instanceof TarefaPrioritaria);

            stmt.setInt(5, tarefa.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa editada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void remover(int id) {

        //vou primeiro usar o metodo buscarPorId para depois pegar e executar esse
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (// try with resources para fechar automaticamente o connection e o prepared statement
            Connection conexao = ConexaoFactory.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);// esse metodo serve para setar o valor "?"

            int linhasAfetadas = stmt.executeUpdate();// quando se altera dados é executeUpdate, é
            // o executeUpdate sempre retorna o numero de linhas afetadas pela alteracao que voce fez no banco de dados

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa removida com sucesso!");
                
            } else {// quando nao da certo de alterar algo no banco de dados, as linhas afetadas sao zero!
                
                System.out.println("Nenhuma tarefa encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    
}
