package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController
{
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
    @FXML private TableColumn<MateriaDTO,String> colAulas_semana;

    @FXML
    private void carregarMateria(){
        MateriaDAO objMateriaDAO = new MateriaDAO();
        ArrayList<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        String nome = txtNome.getText();
        String professor = txtProfessor.getText();
        double notaMedias = Double.parseDouble( txtNotaMedia.getText());
        int aulasSemana = Integer.parseInt( txtAulasSemana.getText());

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(nome);
        objMateriaDTO.setProfessor(professor);
        objMateriaDTO.setNotaMedia(notaMedias);
        objMateriaDTO.setAulasSemana(aulasSemana);

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        carregarMateria();
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtNome.clear();
        txtProfessor.clear();
        txtNotaMedia.clear();
        txtAulasSemana.clear();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        MateriaDTO objMateriaDTO = new MateriaDTO();
        int id= objMateriaDTO.getId();

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.deletarMateria(id);

        carregarMateria();

    }

    @FXML
    public void btnAlterarAction(ActionEvent event){
        String nome = txtNome.getText();
        String professor = txtProfessor.getText();
        double notaMedias = Double.parseDouble( txtNotaMedia.getText());
        int aulasSemana = Integer.parseInt( txtAulasSemana.getText());

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.getId();
        objMateriaDTO.setNome(nome);
        objMateriaDTO.setProfessor(professor);
        objMateriaDTO.setNotaMedia(notaMedias);
        objMateriaDTO.setAulasSemana(aulasSemana);


        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.alterarMateria(objMateriaDTO);

        carregarMateria();

    }
    @FXML
    private void carregarCampos(){
        MateriaDTO objMateriaDTO = tblMateria.getSelectionModel().getSelectedItem();

        if(objMateriaDTO!= null){

            txtNome.setText(objMateriaDTO.getNome());
            txtProfessor.setText(objMateriaDTO.getProfessor());
            txtNotaMedia.setText(String.valueOf( objMateriaDTO.getNotaMedia()));
            txtAulasSemana.setText(String.valueOf( objMateriaDTO.getAulasSemana()));
        }
    }
    private void initialize(URL url, ResourceBundle rb)

    {
        System.out.println("FXML loaded successfully!");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colProfessor.setCellValueFactory(new PropertyValueFactory<>("Professor"));
        colNota_media.setCellValueFactory(new PropertyValueFactory<>("mota_media"));
        colAulas_semana.setCellValueFactory(new PropertyValueFactory<>("aulas_semana"));

        carregarMateria();
    }
}
