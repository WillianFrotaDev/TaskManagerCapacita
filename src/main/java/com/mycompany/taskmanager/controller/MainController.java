/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.dao.TarefaDAO;
import com.mycompany.taskmanager.model.Tarefa;
import com.mycompany.taskmanager.model.TarefaPrioritaria;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private VBox criaTarefa;// area que vai aparecer na hora de editar ou criar uma tarefa é nela que vai ter todos os componentes que estao acima
    
    @FXML 
    private Label labelCriaTarefa;// nele vai dizer se voce esta editando uma tarefa ou adicionando uma tarefa
    
    @FXML 
    private ListView<Tarefa> listaTarefas;// lista de tarefas que aparecera na tela, ela é totalmente independente da listas do backend por isso é preciso repassar as listas para ela
    
    @FXML
    private Label labelCriaGeral;// todo o texto que tera em cada espaco de tarefa na lista de tarefas
    
    private final TarefaDAO tarefaDao = new TarefaDAO();// vai integrar com o banco de dados
    
    private final ListaDeTarefas<TarefaPrioritaria> tarefasPrio = new ListaDeTarefas<>();// guarda no backend as tarefas prioritarias
    private final ListaDeTarefas<Tarefa> tarefas = new ListaDeTarefas<>();// guarda no back end as tarefas normais
    private final TaskManager gerenciador = new TaskManager();// gerencia as duas listas de cima
    
    
    private final ObservableList<Tarefa> tarefasNaTela = FXCollections.observableArrayList();// essa é a lista que alimenta o ListView listaTarefas
    
    private Tarefa editaTarefa;// guarda uma tarefa que vai ser editada no momento
    
    @FXML
    private void initialize() throws SQLException{ // esse metodo so roda uma vez, quando é feito alguma alteracao quem atualiza a lista é atualizarLista
        
        listaTarefas.setItems(tarefasNaTela);// serve para conectar o Observable list com o ListView
        
        listaTarefas.setCellFactory(lista -> new ListCell<>(){// isso serve para configurar como cada tarefa aparece na lista, normalmente ListView funciona com o toString porem com esse ListCell da para editar cada cedula da lista
            
            @Override
            protected void updateItem(Tarefa tarefa, boolean empty){// esse metodo é chamado toda vez que precisar criar ou editar uma celula
                super.updateItem(tarefa, empty);// chama o metodo original da classe ListCell para adicionar novos processos ao metodo
                
                if (empty || tarefa == null) { // verificar se a celula esta vazia ou se a tarefa esta vazia, nesses dois casos vai ser limpa automaticamente
                    // esse processo acontece porque o ListView pode criar celulas extras invisiveis ou reutilizar celulas antigas
                    
                    setText(null);// limpar o texto da celula
                    setStyle("");// limpa o estilo da celula
                    return;// serve para que uma celula vazia nao mostre um texto de uma antiga tarefa
                }
                
                String prioridade = tarefa instanceof TarefaPrioritaria ? "[PRIORIDADE] " : "";// verifica se o objeto tarefa é tarefa prioritaria se for vai ter o texto Prioridade se nao fica sem nada
                String status = tarefa.getConcluida() ? "Concluída" : "Pendente";//usando o metodo que eu criei no backend para verificar e mais na frente atribuir tambem

                setText(prioridade + limparTitulo(tarefa.getTitulo()) + "\n" + tarefa.getDescricao() + "\nStatus: " + status);//Colocar o texto na celula

                if (tarefa.getConcluida()) {
                    setStyle("-fx-text-fill: #607d8b; -fx-background-color: #e8f5e9; -fx-padding: 10;");// se a tarefa estiver concluida muda a cor da tarefa
                } else if (tarefa instanceof TarefaPrioritaria) {
                    setStyle("-fx-text-fill: #263238; -fx-background-color: #fff3e0; -fx-padding: 10;");// se a tarefa for uma tarefa prioritaria
                } else {
                    setStyle("-fx-text-fill: #263238; -fx-padding: 10;");//  tarefa normal
                }
            }
            
        
        });
        atualizarLista();
    }
    
    @FXML
    private void adicionar() {
        editaTarefa = null;// determina que o usuario nao esta editando nenhuma tarefa, para que quando o metodo salvar for usado ele cria uma tarefa nova
        labelCriaTarefa.setText("Nova tarefa");// um texto que fica acima dos campos
        limparCriaTarefa();// serve para limpar os campos e o checkbox
        mostrarAbaCriaTarefa(true);// mostra a aba para o usuario criar a tarefa
        campoTitulo.requestFocus();// ao clicar em editar ou adicionar tarefa ele ja joga na no textfield campoTitulo para o usuario digitar primeiro o titulo
        
    }
    @FXML
    private void salvar() throws SQLException{
        String titulo = campoTitulo.getText().trim();// captura o que foi digitado do campoTitulo
        String descricao = campoDescricao.getText().trim();// captura o que foi digitado do campoDescricao

        if (titulo.isBlank()) {// verificar se o titulo esta vazio
            mostrarAviso("Título obrigatório", "Digite um título para a tarefa.");
            return;
        }

        if (descricao.isBlank()) {// verifica se esta sem descricao, se estiver ele determina a descricao como "Sem descricao"
            descricao = "Sem descrição";
        }
        try{
        if (editaTarefa == null) {// verifica se ele nao esta editando, se nao estiver editando, ele cria uma tarefa e se estiver atualizaTarefa
            criarTarefa(titulo, descricao);
        } else {
            atualizarTarefa(titulo, descricao);
        }

        atualizarLista();// atualiza lista
        limparCriaTarefa();
        mostrarAbaCriaTarefa(false);
        } catch(SQLException e){
            mostrarAviso("Erro no banco de dados", "Não foi possivel salvar a tarefa");
            e.printStackTrace();
        }
    }
    @FXML
    private void editar() {
        Tarefa selecionada = listaTarefas.getSelectionModel().getSelectedItem();// eu busquei uma tarefa que esta selecionada dentro do ListView e atribui a Tarefa selecionada para funcionar como referencia
        // o ListView ja vem com o recurso de selecao entao por isso eu so chamei o metodo
        // SelectionModel é um objeto interno do ListView, ele controla qual item esta selecionado pelo indice e tambem selecao de mais de uma opcao
        // getSelectionModel: retorna o controlador da lista que vai usar o metodo getSelectedItem para buscar o atual item selecionado
        
        // getSelectedItem:retorna o item selecionado no momento
        
        if (selecionada == null) {// se o controlador disser que nao selecinou nada, ele avisa o usuario
            mostrarAviso("Nenhuma tarefa selecionada", "Selecione uma tarefa para editar.");
            return;
        }

        editaTarefa = selecionada; // referenciei de novo porque eu tinha que fazer a verificacao acima
        labelCriaTarefa.setText("Editar tarefa");// titulo da aba
        campoTitulo.setText(limparTitulo(selecionada.getTitulo()));// repassei o titulo da tarefa para a aba de edicao
        campoDescricao.setText(selecionada.getDescricao());// repassei a descricao da tarefa para a aba de edicao
        checkPrioritaria.setSelected(selecionada instanceof TarefaPrioritaria);// repassei o que tinha sido marcado no checkbox antes
        mostrarAbaCriaTarefa(true);// agora sim posso mostrar a aba
        campoTitulo.requestFocus();// ja deixo selecionada o campoTitulo
        
    }
    @FXML
    private void concluir() throws SQLException {
        Tarefa tarefaSelecionada = listaTarefas.getSelectionModel().getSelectedItem();// ele buscou o indice do ListView porque apartir dele que vou concluir a tarefa

        if (tarefaSelecionada == null) {// ListView determina que quando nao tem nada selecionado o valor correspondente em indice é -1
            mostrarAviso("Nenhuma tarefa selecionada", "Selecione uma tarefa para concluir.");
            return;
        }

        //gerenciador.concluirTarefa(tarefas, tarefasPrio, indiceSelecionado + 1);// o metodo que eu criei no backend
        
        tarefaSelecionada.concluir();
        tarefaDao.editar(tarefaSelecionada);
        atualizarLista();
    }
    @FXML
    private void remover() throws SQLException{
        
        Tarefa selecionada = listaTarefas.getSelectionModel().getSelectedItem();// referenciou a propria tarefa selecionada

        if (selecionada == null) {// quando nenhuma tarefa foi selecionada selecionada fica vazia, afinal ela é uma referencia
            mostrarAviso("Nenhuma tarefa selecionada", "Selecione uma tarefa para remover.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);// criar um aviso rapido para confirmar a remocao
        confirmacao.setTitle("Confirmar remoção");// titulo do aviso
        confirmacao.setHeaderText("Remover tarefa");// cabecalho do aviso
        confirmacao.setContentText("Deseja remover: " + limparTitulo(selecionada.getTitulo()) + "?");// o texto contido no aviso

        if (confirmacao.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {// chama o metodo do objeto alert, showAndWait(): serve para mostrar a janela e para o codigo ate o usuario responder
            // esse metodo showAndWait ele retorna a resposta do usuario
            // orElse(): é no caso de nao existir um valor selecionado, ai quando isso acontece ele marca como buttonType.Cancel ai ele compara com o ButtonType.OK
            
            //gerenciador.removerTarefa(tarefas, tarefasPrio, indiceSelecionado + 1);// chama o metodo do gerenciador para remover
            tarefaDao.remover(selecionada.getId());
            atualizarLista();
        }
    }
    
    @FXML
    private void cancelar(){
        limparCriaTarefa();// limpar todas os campos e o checkbox
        mostrarAbaCriaTarefa(false);// fecha a aba de adicionar ou editar tarefa
    }
    
    private void criarTarefa(String titulo, String descricao) throws SQLException {
        Tarefa novaTarefa;
        if (checkPrioritaria.isSelected()) {// se essa checkbox estiver selecionada
            //gerenciador.adicionarTarefaPrioritaria(tarefasPrio, new TarefaPrioritaria(titulo, descricao));
            novaTarefa = new TarefaPrioritaria(titulo, descricao);
        } else {
            //gerenciador.adicionarTarefa(tarefas, new Tarefa(titulo, descricao));
            novaTarefa = new Tarefa(titulo, descricao);
        }
        tarefaDao.salvar(novaTarefa);
        atualizarLista();
    }
    
    private void atualizarTarefa(String titulo, String descricao) {
        
        // Primeiro eu pego tudo do editaTarefa para eu depois limpar ele e usar o que eu peguei dele para colocar em outra tarefa
        //boolean eraPrioritaria = editaTarefa instanceof TarefaPrioritaria;
        boolean deveSerPrioritaria = checkPrioritaria.isSelected();
        boolean estavaConcluida = editaTarefa.getConcluida();
        

        //removerPorReferencia(editaTarefa);// tirei o editaTarefa da lista antes de colocar a nova versao

        Tarefa novaTarefa = deveSerPrioritaria ? new TarefaPrioritaria(titulo, descricao): new Tarefa(titulo, descricao);// cria uma nova tarefa
        
        
        if (estavaConcluida) {// se a tarefa antiga estiver concluida, ele vai concluir a nova agora
            novaTarefa.concluir();
        }
        
        /*if (deveSerPrioritaria) {// para determinar em qual lista adicionar a tarefa
            //tarefasPrio.adicionar((TarefaPrioritaria) novaTarefa);
            novaTarefa.setPrioritaria()
            
        } else {
            //tarefas.adicionar(novaTarefa);
        }*/
        novaTarefa.setId(editaTarefa.getId());
        tarefaDao.editar(novaTarefa);

        editaTarefa = null;// serve para apagar e quando o usuario for fazer o processo de criar uma nova tarefa, ele nao bugue o backend
        // porque la no metodo salvar ele determina se vai criar ou atualizarTarefa
    }
    
    
    private void removerPorReferencia(Tarefa tarefa) {
        for (int i = 0; i < tarefasPrio.tamanhoLista(); i++) {
            if (tarefasPrio.obter(i) == tarefa) {
                tarefasPrio.remover(i);
                return;
            }
        }

        for (int i = 0; i < tarefas.tamanhoLista(); i++) {
            if (tarefas.obter(i) == tarefa) {
                tarefas.remover(i);
                return;
            }
        }

    }

    
    private void atualizarLista() throws SQLException{
        tarefasNaTela.clear();// limpa todas as tarefas para depois mostra-las de novo, vai ser usada no final do initialize para que depois dos ajustes seja atualizada a lista
        ListaDeTarefas<Tarefa> listinhaTare = tarefaDao.listar();
        for (int i = 0; i < listinhaTare.tamanhoLista(); i++) {// adiciona logo as tarefas prioritarias primeiro para depois adicionar as tarefas normais
            tarefasNaTela.add(listinhaTare.obter(i));
        }
        /*for (int i = 0; i < listinhaTare.tamanhoLista(); i++){// adiciona as tarefas normais
            Tarefa tarefinha
            tarefasNaTela.add(tarefas.obter(i));
        }*/
        labelCriaGeral.setText("Total :" + tarefasNaTela.size());// mostra o numero de tarefas
        
    }
    

    
    
    
    private void mostrarAbaCriaTarefa(boolean mostrar){
        criaTarefa.setVisible(mostrar);// torna o layout visivel
        criaTarefa.setManaged(mostrar);// determina se o layout ocupa espaco na tela ou se ele fica so misturado com os outros componentes
        
    }
    
    
    private void limparCriaTarefa(){
        campoTitulo.clear();
        campoDescricao.clear();
        checkPrioritaria.setSelected(false);
        editaTarefa = null;// 
    }
    
    
    private void mostrarAviso(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);// esse Alert cria uma aba para avisar sobre algo, é um metodo ate que bem generico
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    
    
    private String limparTitulo(String titulo){
        return titulo.replace(" | PRIORIDADE ", "").replace(" | TÍTULO: ", "").trim();
    }
    
}
