/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.service;

import com.mycompany.taskmanager.model.Usuario;
import com.mycompany.taskmanager.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author willianfrota
 */
@Service
public class UsuarioService {
    
    private final UsuarioRepository userRepo;
    
    public UsuarioService(UsuarioRepository userRepo){
        this.userRepo = userRepo;
    }
    
    
    public void atualizarUsuario(String nome, String email, int id, String senha){
        
        
        Usuario usuario = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado no banco de dados!"));
        // na duas linhas acima faz a seguinte coisa: se nao encontrar o id do usuario logo ele dispara um erro, se ele encontrar ele atribui à variavel de referencia para fazermos as alteracoes
        // o metodo .findById eh do ti
        
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        userRepo.save(usuario);// .save tem o papel tanto de salvar como de atualizar: INSERT e UPDATE
        
    }
}
