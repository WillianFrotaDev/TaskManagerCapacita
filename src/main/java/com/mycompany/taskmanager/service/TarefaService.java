/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.service;

import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import com.mycompany.taskmanager.model.Usuario;
import com.mycompany.taskmanager.repository.TarefaRepository;
import org.springframework.stereotype.Service;
/**
 *
 * @author willianfrota
 */
@Service
public class TarefaService {
    
    private TarefaRepository tarefaRepo;
    
    
    public TarefaService(TarefaRepository tarefarepo){
        
        this.tarefaRepo = tarefarepo;
    }
    
    public void salvarTarefa(Tarefa tarefinha){
        
        String titulo = tarefinha.getTitulo();
        if(titulo == null || titulo.trim().isEmpty()){
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio!");
        }
        tarefaRepo.save(tarefinha);
        
        
    }
    public void removerTarefa(Tarefa tarefinha){
        int id = tarefinha.getId();
        
        tarefaRepo.deleteById(id);
        
    }
    
    public void atualizaTarefa(Tarefa tarefinha, String tituloNova, String descricaoNova, boolean prioridadeNova, boolean concluidaNova, Usuario usuario){
        
        
        
        if (tituloNova == null || tituloNova.trim().isEmpty()) {
        throw new IllegalArgumentException("O título da tarefa não pode ser vazio!");
    }
     
        boolean ehPrioridade = tarefinha instanceof TarefaPrioritaria;
        
        if (ehPrioridade && prioridadeNova){
            tarefinha = new TarefaPrioritaria(tarefinha.getTitulo(), tarefinha.getDescricao(), tarefinha.getUsuario());
            
        }
        // o dirty checking do hibernate faz a verificação automaticamente do que foi alterado sem que seja necessario eu verificar manualmente para não haver trafego desnecessario para o banco de dados
        tarefinha.setTitulo(tituloNova);
        tarefinha.setDescricao(descricaoNova);
        tarefinha.setConcluida(concluidaNova);
        
        
        
        
        
    }   
    
}
