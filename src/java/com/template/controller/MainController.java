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
    /*
    * UX:
    * Reabilita o botão salvar para novos cadastros
    * Verificação de campos vazios
    * Desabilita o Salvar durante edição para evitar cadastros acidentais
    * Impede que o usuário digite letras onde só vão números
    * MENSAGEM
    *
    * UI :
    * Placholders
    * Não habilita os botões excluir e editar não ficam desabilitados se não tiver nada selecionado
    * MOSTRAR CAMPOS OBRIGATORIOS
    * Imagem/logo
    * */
    @FXML
    private void carregarMateria(){
        MateriaDAO objMateriaDAO = new MateriaDAO();
        ArrayList<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        if (!validarCampos()) return;

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(txtNome.getText());
        objMateriaDTO.setProfessor(txtProfessor.getText());
        objMateriaDTO.setNotaMedia(Double.parseDouble(txtNotaMedia.getText()));
        objMateriaDTO.setAulasSemana(Integer.parseInt(txtAulasSemana.getText()));

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        DialogUtil.mensagemConfirmacao("Matéria salva com sucesso! ");
        carregarMateria();
        btnLimparAction(null);
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtNome.clear();
        txtProfessor.clear();
        txtNotaMedia.clear();
        txtAulasSemana.clear();

        tblMateria.getSelectionModel().clearSelection();
        btnSalvar.setDisable(false);

        if (event != null && lblMensagem != null) {
            lblMensagem.setText("");
        }
        DialogUtil.mensagemInfo("Os campos estão limpos! ");
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.deletarMateria(selecionada.getId());

            DialogUtil.mensagemConfirmacao("Matéria excluída com sucesso!");
            carregarMateria();
            btnLimparAction(null);
        }
    }

    @FXML
    public void btnAlterarAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();

        if (selecionada != null) {
            if (!validarCampos()) return;

            MateriaDTO objMateriaDTO = new MateriaDTO();
            objMateriaDTO.setId(selecionada.getId());
            objMateriaDTO.setNome(txtNome.getText());
            objMateriaDTO.setProfessor(txtProfessor.getText());
            objMateriaDTO.setNotaMedia(Double.parseDouble(txtNotaMedia.getText()));
            objMateriaDTO.setAulasSemana(Integer.parseInt(txtAulasSemana.getText()));

            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.alterarMateria(objMateriaDTO);

            DialogUtil.mensagemConfirmacao("Matéria alterada com sucesso!");
            carregarMateria();
            btnLimparAction(null);
        }
    }

    @FXML
    private void carregarCampos(MouseEvent event){
        MateriaDTO objMateriaDTO = tblMateria.getSelectionModel().getSelectedItem();

        if(objMateriaDTO != null){
            txtNome.setText(objMateriaDTO.getNome());
            txtProfessor.setText(objMateriaDTO.getProfessor());
            txtNotaMedia.setText(String.valueOf(objMateriaDTO.getNotaMedia()));
            txtAulasSemana.setText(String.valueOf(objMateriaDTO.getAulasSemana()));

            btnSalvar.setDisable(true);
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

        carregarMateria();
    }


    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty() || txtProfessor.getText().trim().isEmpty() ||
                txtNotaMedia.getText().trim().isEmpty() || txtAulasSemana.getText().trim().isEmpty()) {

            DialogUtil.mensagemErro("Atenção: Preencha todos os campos obrigatórios!");
            return false;
        }
        return true;
    }

    private void restringirEntradaNumerica(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                campo.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }
}