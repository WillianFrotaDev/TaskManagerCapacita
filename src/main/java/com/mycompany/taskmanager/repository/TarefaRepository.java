/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.repository;

import com.mycompany.taskmanager.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author willianfrota
 */
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
    // essa interface vai ser usada para fazer a ponte entre a regra de negocio e as interações com o banco de dados
    
    // com essa interface que extends JpaRepository ganhamos varios metodos automaticamente
    // findById(), deleteById(), save() e etc
}
