/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 *
 * @author willianfrota
 */
public class MainController {
    @FXML
    private TextField campoTitulo;
    @FXML
    private TextArea campoDescricao;
    
    private ListaDeTarefas<Tarefa> tarefas = new ListaDeTarefas<>();
    private TaskManager gerenciador = new TaskManager();
    @FXML
    private CheckBox checkPrioridade;
    @FXML 
    private ListView<Tarefa> listaTarefas = new ListView<>();
    @FXML
    private void adicionar() {
        formNovaTarefa.setVisible(true);
        formNovaTarefa.setManaged(true);

        campoTitulo.clear();
        campoDescricao.clear();
        campoTitulo.requestFocus();
        
    }

    @FXML
    private void remover() {}

    @FXML
    private void editar() {}

    @FXML
    private void concluir() {}
    
}
