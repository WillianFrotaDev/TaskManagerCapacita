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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author willianfrota
 */
public class TarefaDAO {
    
    // eu criei essa conexao para poder usar nos testes
    private final Connection conexao;
    
    private final String sqlVerifica = """
                        CREATE TABLE IF NOT EXISTS tarefas (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            titulo TEXT NOT NULL,
                            descricao TEXT,
                            concluida BOOLEAN DEFAULT FALSE,
                            prioritaria BOOLEAN DEFAULT FALSE
                        )
                         """;// cria o banco caso ele nao exista 
    
    public TarefaDAO(Connection conexao) throws SQLException{// ser usado nos testes, porque a conexao ao SQLite eh diferente do MYSQL
        this.conexao = conexao;
        seNaoTiverTabela();
    }
    public void seNaoTiverTabela() throws SQLException{// esse metodo facilitar a verificaçao no decorrer do DAO inteiro
        try(PreparedStatement stmVerifica = conexao.prepareStatement(sqlVerifica)){
            stmVerifica.executeUpdate();
            
        }
        
    }
    
    
    public ListaDeTarefas<Tarefa> listar() throws SQLException{// retorna a Lista para o ListView
      
        ListaDeTarefas<Tarefa> listinha = new ListaDeTarefas<>();
        String sql = """
                     SELECT * FROM tarefas
                     ORDER BY prioritaria DESC
                     """;// isso serve para ordenar as tarefas sendo as prioritarias primeiro
        
        //Foram instaciados como nulo para verificar no final se foram instaciados

        try (
            
            PreparedStatement stm = conexao.prepareStatement(sql); // preparou o comando
            ResultSet resultadoConsulta = stm.executeQuery()//// serviu executar e para pegar o resultado da consulta ao banco de dados    
            ){
            
            
            
            
             while (resultadoConsulta.next()) {// esse resultadoConsulta.next() devolve booleano, true quando tem alguma linha de resultado a frente e false quando nao tem
                 //enquanto tiver linhas a frente ele se encaminha para a linha e retorna true, logo ele so sai desse loop quando nao tiver mais linhas

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
            
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listinha;
    }
    
    public <T extends Tarefa> void salvar(T tarefa) throws SQLException{
        
        String sql = "INSERT INTO tarefas (titulo, descricao, concluida, prioritaria) VALUES (?, ?, ?, ?)";
        
        
        
        
        try(
            
            PreparedStatement stm = conexao.prepareStatement(sql);//PreparedStatement serve para executar comandos SQL no banco de dados, porem voce primeira cria depois executa
                
                
            ){
           
           
           
           conexao.setAutoCommit(false);
           
           
           // isso representa a ordem de cada um dos elementos associado a string sql la de cima
           stm.setString(1, tarefa.getTitulo());
           stm.setString(2, tarefa.getDescricao());
           stm.setBoolean(3, tarefa.getConcluida());
           stm.setBoolean(4, tarefa.getPrioridade());
           
           //executeUpdate: altera dados
           //executeQuery: busca dados
           stm.executeUpdate();// ele é usado para operacoes em sql, usado nas operacoes INSERT, UPDATE e DELETE, Ele tambem pode retornar um valor inteiro de linhas alteradas quando atribuido a uma variavel inteira
           //tambem usado na criacao de tabelas (CREATE TABLE) e em alteracoes estruturais (ALTER TABLE)
           
           conexao.commit();
           
           
        } catch (SQLException e) {
            try {
                if(conexao != null){
                    conexao.rollback();
                }
            } catch(SQLException erroRollback){
                erroRollback.printStackTrace();
            }
        } finally{
            
            try{
                conexao.setAutoCommit(true);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
    
    public Tarefa buscarPorId(int id) throws SQLException{
        
        String sql = "SELECT * FROM tarefas WHERE id = ?";
        
        
        
        
        try(
            PreparedStatement state = conexao.prepareStatement(sql);
             // executeQuery serve para pegar a devolucao do comando
                    ){
            
            
            
            state.setInt(1, id);
            try(
                    ResultSet resultadoConsulta = state.executeQuery()
                ){
            
        
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
                    
                    return tarefa;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            } 
        
    
    
    
        return null;// se ele nao encontrar ele retorna null
    
        
    }
    
    public void editar(Tarefa tarefa) {
        
        // vou usar junto com buscarPorId
        String sql = """
        UPDATE tarefas
        SET titulo = ?, descricao = ?, concluida = ?, prioritaria = ?
        WHERE id = ?""";
        
        
        try(
            
            PreparedStatement stmt = conexao.prepareStatement(sql)
            ){ 
            
            
            
            conexao.setAutoCommit(false);
            
            
         

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setBoolean(3, tarefa.getConcluida());

            // verifica se eh prioritaria, para ja atribuir ao comando que sera executado
            stmt.setBoolean(4, tarefa instanceof TarefaPrioritaria);

            stmt.setInt(5, tarefa.getId());
            
            
            int linhasAfetadas = stmt.executeUpdate();
            conexao.commit();
            
            if (linhasAfetadas > 0) {
                System.out.println("Tarefa editada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada.");
            }

        } catch (SQLException e) {
            try{
                if(conexao != null){
                    conexao.rollback();
                    
                }
            } catch(SQLException erroRoll){
                erroRoll.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try{
                conexao.setAutoCommit(true);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void remover(int id) {
        
        //vou primeiro usar o metodo buscarPorId para depois pegar e executar esse
        String sql = "DELETE FROM tarefas WHERE id = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){// try with resources para fechar automaticamente o connection e o prepared statement
          
            conexao.setAutoCommit(false);
            
         

            stmt.setInt(1, id);// esse metodo serve para setar o valor "?"
            
            int linhasAfetadas = stmt.executeUpdate();// quando se altera dados é executeUpdate, quando voce determina um tipo int ele retorna o numero de linhas afetadas
            // o executeUpdate ele roda o comando em SQL e quando atribuido a tipo int retorna o numero de linhas afetadas
            conexao.commit();
            
            if (linhasAfetadas > 0) {
                System.out.println("Tarefa removida com sucesso!");
                
            } else {// quando nao da certo de alterar algo no banco de dados, as linhas afetadas sao zero!
                
                System.out.println("Nenhuma tarefa encontrada.");
            }

        } catch (SQLException e) {
            try{
                if(conexao != null){
                    conexao.rollback();
                }
            } catch(SQLException erroRoll){
                erroRoll.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try{
                conexao.setAutoCommit(true);
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    
    }
    
    
}
