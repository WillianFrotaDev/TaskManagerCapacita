/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
/**
 *
 * @author willianfrota
 */
public class MainController {
    
    @FXML
    private TextField campoTitulo;//lugar para digitar o titulo da tarefa a ser adicionada ou editada
    
    @FXML
    private TextArea campoDescricao;//lugar para digitar a descricao da tarefa
    
    @FXML 
    private CheckBox checkPrioritaria;// uma checkbox para determinar se a tarefa a ser editada ou criada é tarefa normal ou se é tarefa prioritaria
    
    @FXML
    private VBox criaTarefa;// area que vai aparecer na hora de editar ou criar uma tarefa é nela que vai ter todos os componentes acima
    
    @FXML 
    private Label labelCriaTarefa;// nele vai dizer se voce esta editando uma tarefa ou adicionando uma tarefa
    
    @FXML 
    private ListView<Tarefa> listaTarefas;// lista de tarefas que aparecera na tela, ela é totalmente independente da listas do backend por isso é preciso repassar as listas para ela
    
    @FXML
    private Label labelCriaGeral;// todo o texto que tera em cada espaco de tarefa na lista de tarefas
    
    private final ListaDeTarefas<TarefaPrioritaria> tarefasPrio = new ListaDeTarefas<>();// guarda no backend as tarefas prioritarias
    private final ListaDeTarefas<Tarefa> tarefas = new ListaDeTarefas<>();// guarda no back end as tarefas normais
    private final TaskManager gerenciador = new TaskManager();// gerencia as duas listas de cima
    private final ObservableList<Tarefa> tarefasNaTela = FXCollections.observableArrayList();// essa é a lista que alimenta o ListView listaTarefas
    
    private Tarefa editaTarefa;// guarda uma tarefa que vai ser editada no momento
    
    @FXML
    private void initialize(){ // esse metodo ele é ativado automaticamente quando a tela FXML termina de carregar, toda vez que voce adicionar, remover ou editar ele vai ativar esse metodo
        
        listaTarefas.setItems(tarefasNaTela);// serve para mostrar os dados de outra lista, detalhe toda vez que
        listaTarefas.setCellFactory(lista -> new ListCell<>(){
         
            
        
        });
        
    }
    
    @FXML
    private void adicionar() {
        
        
    }

    @FXML
    private void remover() {}

    @FXML
    private void editar() {}

    @FXML
    private void concluir() {}
    
}
