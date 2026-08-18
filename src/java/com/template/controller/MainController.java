package com.template.controller;

import com.template.model.dto.MateriaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.template.carregamento.Carregamento.*;
import static com.template.helper.MainControllerHelper.*;
import static com.template.util.DialogUtil.*;
import static com.template.validator.MateriaValidador.*;

public class MainController {

    @FXML private TextField txtNome;
    @FXML private TextField txtProfessor;
    @FXML private TextField txtNotaMedia;
    @FXML private TextField txtAulasSemana;
    @FXML private Button btnExcluir;
    @FXML private Button btnAlterar;
    @FXML private TableView<MateriaDTO> tblMateria;
    @FXML private TableColumn<MateriaDTO, String> colId;
    @FXML private TableColumn<MateriaDTO, String> colNome;
    @FXML private TableColumn<MateriaDTO, String> colProfessor;
    @FXML private TableColumn<MateriaDTO, String> colNota_media;
    @FXML private TableColumn<MateriaDTO, String> colAula_semana;

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        Salvar(txtNome.getText(), txtProfessor.getText(), txtNotaMedia.getText(), txtAulasSemana.getText(), tblMateria, txtNome, txtProfessor, txtNotaMedia, txtAulasSemana);
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        Limpar(txtNome, txtProfessor, txtNotaMedia, txtAulasSemana, tblMateria);
        mensagemInfo("Os campos estão limpos!");
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            Excluir(selecionada.getId(), tblMateria, txtNome, txtProfessor, txtNotaMedia, txtAulasSemana);
        }
    }

    @FXML
    public void btnAlterarAction(ActionEvent event) {
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            Alterar(
                    selecionada.getId(),
                    txtNome.getText(),
                    txtProfessor.getText(),
                    txtNotaMedia.getText(),
                    txtAulasSemana.getText(),
                    tblMateria,
                    txtNome, txtProfessor, txtNotaMedia, txtAulasSemana
            );
        }
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

        // Evento ao selecionar linha da tabela para carregar nos campos
        tblMateria.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            carregarCamposMainController(tblMateria, txtNome, txtProfessor, txtNotaMedia, txtAulasSemana);
        });

        carregarMateria(tblMateria);
    }
}