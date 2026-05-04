/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.app;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 *
 * @author willianfrota
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/taskmanager/view/main.fxml")
        );
        

        Scene scene = new Scene(loader.load());
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    
    
    
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
