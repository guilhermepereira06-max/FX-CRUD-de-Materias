package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MainController {

    @FXML private Button btnSalvar;
    @FXML private TextField txtNome;
    @FXML private TextField txtProfessor;
    @FXML private TextField txtNotaMedia;
    @FXML private TextField txtAulasSemana;
    @FXML private Button btnExcluir;
    @FXML private Button btnAlterar;
    @FXML private Button btnLimpar;
    @FXML private TableView<MateriaDTO> tblMateria;
    @FXML private TableColumn<MateriaDTO,String> colId;
    @FXML private TableColumn<MateriaDTO,String> colNome;
    @FXML private TableColumn<MateriaDTO,String> colProfessor;
    @FXML private TableColumn<MateriaDTO,String> colNota_media;
    @FXML private TableColumn<MateriaDTO,String> colAula_semana;

    @FXML
    private void carregarMateria(){
        // Recupera os dados e coloca na tabela
        MateriaDAO objMateriaDAO = new MateriaDAO();
        ArrayList<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }

    // Criar uma nova materia
    @FXML
    private void btnSalvarAction(ActionEvent event){
        String nome = txtNome.getText();
        String professor = txtProfessor.getText();
        double notaMedias = Double.parseDouble(txtNotaMedia.getText());
        int aulasSemana = Integer.parseInt(txtAulasSemana.getText());

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(nome);
        objMateriaDTO.setProfessor(professor);
        objMateriaDTO.setNotaMedia(notaMedias);
        objMateriaDTO.setAulasSemana(aulasSemana);

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        // Atualiza a tabela e limpa os campos
        carregarMateria();
        btnLimparAction(null);
    }

    //Limpando os campos de texto
    @FXML
    private void btnLimparAction(ActionEvent event){
        txtNome.clear();
        txtProfessor.clear();
        txtNotaMedia.clear();
        txtAulasSemana.clear();
    }

    //Botao que exclui a materia selecionada
    @FXML
    private void btnExcluirAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.deletarMateria(selecionada.getId());

        carregarMateria();
        btnLimparAction(null);
    }

    //botao que altera a materia selecionada
    @FXML
    public void btnAlterarAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();

        String nome = txtNome.getText();
        String professor = txtProfessor.getText();
        double notaMedias = Double.parseDouble(txtNotaMedia.getText());
        int aulasSemana = Integer.parseInt(txtAulasSemana.getText());

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setId(selecionada.getId());
        objMateriaDTO.setNome(nome);
        objMateriaDTO.setProfessor(professor);
        objMateriaDTO.setNotaMedia(notaMedias);
        objMateriaDTO.setAulasSemana(aulasSemana);

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.alterarMateria(objMateriaDTO);

        carregarMateria();
        btnLimparAction(null);
    }

    @FXML
    private void carregarCampos(MouseEvent event){
        MateriaDTO objMateriaDTO = tblMateria.getSelectionModel().getSelectedItem();

        if(objMateriaDTO != null){
            // preenche campos com valores da linha selecionada
            txtNome.setText(objMateriaDTO.getNome());
            txtProfessor.setText(objMateriaDTO.getProfessor());
            txtNotaMedia.setText(String.valueOf(objMateriaDTO.getNotaMedia()));
            txtAulasSemana.setText(String.valueOf(objMateriaDTO.getAulasSemana()));
        }
    }

    @FXML
    public void initialize() {
        // Configura as colunas da tabela com os nomes das propriedades do DTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colNota_media.setCellValueFactory(new PropertyValueFactory<>("notaMedia"));
        colAula_semana.setCellValueFactory(new PropertyValueFactory<>("aulasSemana"));

        carregarMateria();
    }
}
