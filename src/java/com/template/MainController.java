package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private TextField txtnome;
    @FXML private TextField txtProfessor;
    @FXML private TextField txtNotaMedia;
    @FXML private TextField txtAulasSemana;
    @FXML private Button btnExcluir;
    @FXML private Button btnAlterar;
    @FXML private TableView<MateriaDTO> tblMateria;
    @FXML private TableColumn<MateriaDTO,String> colId;
    @FXML private TableColumn<MateriaDTO,String> colNome;
    @FXML private TableColumn<MateriaDTO,String> colProfessor;
    @FXML private TableColumn<MateriaDTO,String> colNota_media;
    @FXML private TableColumn<MateriaDTO,String> colAulas_semana;

    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
