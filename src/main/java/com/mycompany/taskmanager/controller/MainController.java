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
        
        listaTarefas.setCellFactory(lista -> new ListCell<>(){// isso serve para configurar como cada tarefa aparece na lista, normalmente ListView funciona com o toString porem com esse ListCell da para editar cada cedula da lista
            
            @Override
            protected void updateItem(Tarefa tarefa, boolean empty){// esse metodo é chamado toda vez que precisa atualizar a lista alterando algum item
                super.updateItem(tarefa, empty);// chama o metodo original da classe ListCell para adicionar novos processos ao metodo
                
                if (empty || tarefa == null) { // verificar se a celular esta vazia ou se a tarefa esta vazia, nesses dois casos vai ser limpa automaticamente
                    // esse processo acontece porque o ListView pode criar celulas extras invisiveis ou reutilizar celulas antigas
                    
                    setText(null);// limpar o texto da celula
                    setStyle("");// limpa o estilo da celula
                    return;// serve para que uma celula vazia nao mostre um texto de uma antiga tarefa
                }
                
                String prioridade = tarefa instanceof TarefaPrioritaria ? "[PRIORIDADE] " : "";// verifica se o objeto tarefa é tarefa prioritaria se for vai ter o texto Prioridade se nao fica sem nada
                String status = tarefa.getConcluida() ? "Concluída" : "Pendente";//usando o metodo que eu criei no backend para verificar e mais na frente atribuir tambem

                setText(prioridade + limparTitulo(tarefa.getTitulo()) + "\n" + tarefa.getDescricao() + "\nStatus: " + status);

                if (tarefa.getConcluida()) {
                    setStyle("-fx-text-fill: #607d8b; -fx-background-color: #e8f5e9; -fx-padding: 10;");
                } else if (tarefa instanceof TarefaPrioritaria) {
                    setStyle("-fx-text-fill: #263238; -fx-background-color: #fff3e0; -fx-padding: 10;");
                } else {
                    setStyle("-fx-text-fill: #263238; -fx-padding: 10;");
                }
            }
            
        
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
    
    @FXML
    private String limparTitulo(String titulo){
        return titulo.replace(" | PRIORIDADE ", "").replace(" | TÍTULO: ", "");
    }
    
}
