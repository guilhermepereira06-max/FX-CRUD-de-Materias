package com.template.controller;

import com.template.util.DialogUtil;
import com.template.model.dao.MateriaDAO;
import com.template.model.dto.MateriaDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static com.template.validator.MateriaValidador.*;
import static com.template.helper.MainControllerHelper.*;

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
    @FXML private Label lblMensagem;

    @FXML
    private void carregarMateria(){
        MateriaDAO objMateriaDAO = new MateriaDAO();
        ArrayList<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        Salvar(txtNome.getText(),txtProfessor.getText(),txtNotaMedia.getText(),txtAulasSemana.getText());
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        Limpar(null);
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        Excluir(selecionada.getId());
    }

    @FXML
    public void btnAlterarAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        Alterar(selecionada.getId());
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colNota_media.setCellValueFactory(new PropertyValueFactory<>("notaMedia"));
        colAula_semana.setCellValueFactory(new PropertyValueFactory<>("aulasSemana"));

        tblMateria.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        restringirEntradaNumerica(txtNotaMedia);
        restringirEntradaNumerica(txtAulasSemana);

        btnExcluir.disableProperty().bind(tblMateria.getSelectionModel().selectedItemProperty().isNull());
        btnAlterar.disableProperty().bind(tblMateria.getSelectionModel().selectedItemProperty().isNull());

        carregarMateria();
    }



}