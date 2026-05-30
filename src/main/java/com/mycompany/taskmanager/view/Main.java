/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskmanager.view;
import com.mycompany.taskmanager.controller.ListaDeTarefas;
import com.mycompany.taskmanager.controller.TaskManager;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author willianfrota
 */
public class Main {

    public static void main(String[] args) {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        TaskManager gerenciador = new TaskManager();
        ListaDeTarefas<Tarefa> listaTarefa = new ListaDeTarefas<>();
        ListaDeTarefas<TarefaPrioritaria> listaTarefaPrio = new ListaDeTarefas<>();
        
        do{
            System.out.println("============GERENCIADOR DE TAREFAS============");
            System.out.println("1- Adicionar tarefa");
            System.out.println("2- Listar tarefas");
            System.out.println("3- Concluir tarefa");
            System.out.println("4- Remover tarefa");
            System.out.println("5- Sair");
            
            int operacao;
            try{
                operacao = Integer.parseInt(sc.nextLine());      //nesse caso sc.nextInt() geraria outra excecao 
            } catch(NumberFormatException e){
                System.out.println("Operacao invalida");
                continue;
            }
            
            
            switch(operacao){
                case 1:
                    System.out.println("Diga o titulo dessa tarefa:");
                    String titulo = sc.nextLine();
                    System.out.println("Diga a descricao dessa tarefa:");
                    String descricao = sc.nextLine();
                    System.out.println("Essa tarefa é prioridade? (s / n)");
                    String ehPrioridade = sc.nextLine();
                    boolean prioridade = false;
                    Tarefa tarefa;
                    TarefaPrioritaria tarefaPrio;
                    if(ehPrioridade.equalsIgnoreCase("s")){
                        tarefaPrio = new TarefaPrioritaria(titulo, descricao);
                        gerenciador.adicionarTarefaPrioritaria(listaTarefaPrio, tarefaPrio);
                        
                    } else if(ehPrioridade.equalsIgnoreCase("n")){
                        tarefa = new Tarefa(titulo, descricao);
                        gerenciador.adicionarTarefa(listaTarefa, tarefa);
                        
                        
                    } else{
                        System.out.println("Já que voce digitou errado, não é prioridade");
                    }
                    break;
                    
                case 2:
                    gerenciador.listarTarefas(listaTarefa, listaTarefaPrio);
                    break;
                case 3:
                    gerenciador.listarTarefas(listaTarefa, listaTarefaPrio);
                    try{
                        System.out.println("\n Diga um indice:");
                        int indice = Integer.parseInt(sc.nextLine());
                        gerenciador.concluirTarefa(listaTarefa, listaTarefaPrio,indice);
                    } catch(NumberFormatException e){
                        System.out.println("Digite um numero!");
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Esse numero nao esta na lista");
                    }
                    break;
                case 4:
                    gerenciador.listarTarefas(listaTarefa, listaTarefaPrio);
                    try{
                        System.out.println("\n Diga um indice:");
                        int indice = Integer.parseInt(sc.nextLine());
                        gerenciador.removerTarefa(listaTarefa, listaTarefaPrio,indice);
                    } catch(NumberFormatException e){
                        System.out.println("Digite um numero!");
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Esse numero nao esta na lista");
                    }
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        } while(!sair);
        
        
    }
}
