/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.app;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
/**
 *
 * @author willianfrota
 */
public class App extends Application {
    private ListView<String> listaVisual = new ListView<>();
    @Override
    public void start(Stage stage){
        Label titulo = new Label("Gerenciador de Tarefas");
        Label texto = new Label("Javafx funcionando!");
        VBox layout = new VBox(texto);
        
        layout.setStyle("-fx-padding: 20; -fx-font-size: 20px;");

        Scene scene = new Scene(layout, 400, 300);

        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    
    
    
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
