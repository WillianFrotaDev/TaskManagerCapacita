/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willianfrota
 */
@Entity // determina a classe como entidade para que possa ir pro banco
@Table(name = "usuarios") // determina qual tabela do banco essa classe vai
public class Usuario {
    
    @Id // o ID de identificação la no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // aqui diz que o valor vai ser gerado automaticamente para ser uma chave primaria de identificaçao
    private int id;// chave primaria
    
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;
    
    @Column(name = "senha", length = 20, nullable = false)
    private String senha;
    
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    
    
    
    // cascade serve para salvar tudo de uma vez, ao salvar a entidade usuario tambem salva suas tarefas
    //esse fetch serve para dizer como vai ser o carregamento dessa entidade no banco, se vai carregar as tarefas junto ao usuario(EAGER) ou somente o usuario (LAZY) com seus outros atributos como id e etc
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)// nao eh o nome da tabela em que ou da classe. É o nome da variavel inicializada na outra classe
    private List<Tarefa> tarefas = new ArrayList<>();// isso serve para criar uma relacao usuario e tarefas, isso funciona por causa do mappedBy
    
    
    public Usuario(){} // precisa por que ao consultar o banco o hibernate faz uma conversao das linhas da tabela com objetos java e ele usa esse construtor para isso
    
    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    
    
    // o bom dos metodos addTarefa e removeTarefa quee eles ajusta o valor das duas tabelas de uma vez so
    public void addTarefa(Tarefa tarefa){// quando for criado uma tarefa ela tem que ser adicionada a lista tarefas do seu respectivo usuario
        
        tarefas.add(tarefa);// adiciona a tarefa na lista do usuario que criou a tarefa
        
        tarefa.setUsuario(this);// informa a tarefa qual é o seu usuario
    }
    
    public void removeTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);
        tarefa.setUsuario(null);
    }
    
    //Tambem precisa de ter todos os getters e setters para poder pegar cada informacao de cada objeto criado pelo hibernate
    
    public int getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
}
