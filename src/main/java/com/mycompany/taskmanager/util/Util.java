/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.util;

import com.mycompany.taskmanager.controller.TaskManager;
import java.util.Scanner;


/**
 *
 * @author willianfrota
 */
public class Util {
    public static void menu(int operacao, boolean sair){
        
        TaskManager gerenciador = new TaskManager();
        Scanner sc1 = new Scanner(System.in);
        switch(operacao){
                case 1:
                    System.out.println("Essa tarefa é prioridade? (s / n)");
                    String ehPrioridade = sc1.nextLine();
                    boolean prioridade = false;
                    if(ehPrioridade.equalsIgnoreCase("s")){
                        prioridade = true;
                    } else if(ehPrioridade.equalsIgnoreCase("n")){
                        prioridade = false;
                    } else{
                        System.out.println("Já que voce digitou errado, não é prioridade");
                    }
                    System.out.println("Diga o titulo dessa tarefa:");
                    String titulo = sc1.nextLine();
                    System.out.println("Diga a descricao dessa tarefa:");
                    String descricao = sc1.nextLine();
                    gerenciador.adicionarTarefa(titulo, descricao, prioridade);

                    break;
                case 2:
                    gerenciador.listarTarefas();
                    break;
                case 3:
                    gerenciador.listarTarefas();
                    try{
                        System.out.println("\n Diga um indice:");
                        int indice = Integer.parseInt(sc1.nextLine());
                        gerenciador.concluirTarefa(indice);
                    } catch(NumberFormatException e){
                        System.out.println("Digite um numero!");
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Esse numero nao esta na lista");
                    }
                    break;
                case 4:
                    gerenciador.listarTarefas();
                    try{
                        System.out.println("\n Diga um indice:");
                        int indice = Integer.parseInt(sc1.nextLine());
                        gerenciador.removerTarefa(indice);
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
    
    }
    
}
