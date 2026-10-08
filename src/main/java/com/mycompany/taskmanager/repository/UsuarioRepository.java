/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.repository;

import com.mycompany.taskmanager.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 *
 * @author willianfrota
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findByNome(String nome);// muito pratico, ele ja conseguiu entender sozin que eu quero buscar pelo nome
    Optional<Usuario> findByEmail(String email);// mas voce precisa colocar o mesmo nome de atributo da classe Usuario
    
    
    
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha")
    Optional<Usuario> fazerLogin(String nome, String senha);// fazer login
}
