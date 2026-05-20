/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.app;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author willianfrota
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/com/mycompany/taskmanager/view/main.fxml"));
        // cria o carregador FXMLLoader para ler esse arquivo e encontra o arquivo (.fxml)
        // FXMLLoader é a classe que le o xml do FXML, cria componentes javafx; conecta controller,@FXML e onAction
        // getResource serve para localizar o arquivo dentro do projeto
        // getClass serve para pegar a classe atual do objeto (nesse caso com.mycompany.taskmanager.app.App) quando nao esta associado a um objeto ele usa a classe que voce esta editando
        // url: Uniform Resource Locator
        // nesse caso ele aponta para o caminho do FXML compilado, que nesse caso seria o caminho da classe atual
        // entendi agora apartir da classe a atual, ela tem um metodo chamado getResource que determina onde esta o caminho do arquivo fxmlé
        
        Parent root = loader.load();// diferente de usar o loader.load direto no scene é que da pra estilizar o root antes de carregar a janela
        // usando o root da para reutilizar o codigo

        Scene scene = new Scene(root, 900, 600);// width: largura = 900 e height: altura = 600
        stage.setMinWidth(820);// setar a largura minima
        stage.setMinHeight(520);// setar a altura minima
        stage.setTitle("Task Manager");// ja colocar um titulo na janela
        stage.setScene(scene);// carregar a scene
        stage.show();// mostrar o stage, uma traducao ao pe da letra seria estagio atual da tela
    
    
    
    }
    public static void main(String[] args) {
        launch(args);// isso inicia a aplicacao javafx, usado para chamar o metodo start criado la em cima
    }
    
    
}
