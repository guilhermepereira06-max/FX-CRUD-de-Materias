package com.template;

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
import javafx.scene.paint.Color;

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

    // Nova injeção para UI/UX: Label de mensagens ao usuário
    @FXML private Label lblMensagem;

    @FXML
    private void carregarMateria(){
        MateriaDAO objMateriaDAO = new MateriaDAO();
        ArrayList<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event){
        if (!validarCampos()) return; // UX: Validação de campos vazios

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(txtNome.getText());
        objMateriaDTO.setProfessor(txtProfessor.getText());
        objMateriaDTO.setNotaMedia(Double.parseDouble(txtNotaMedia.getText()));
        objMateriaDTO.setAulasSemana(Integer.parseInt(txtAulasSemana.getText()));

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        exibirMensagem("Matéria salva com sucesso!", Color.GREEN);
        carregarMateria();
        btnLimparAction(null);
    }

    @FXML
    private void btnLimparAction(ActionEvent event){
        txtNome.clear();
        txtProfessor.clear();
        txtNotaMedia.clear();
        txtAulasSemana.clear();
        
        tblMateria.getSelectionModel().clearSelection(); // UI/UX: Remove o foco da tabela
        btnSalvar.setDisable(false); // UX: Reabilita o botão salvar para novos cadastros
        
        // Limpa a mensagem caso tenha sido chamada pelo botão "Limpar"
        if (event != null && lblMensagem != null) {
            lblMensagem.setText("");
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.deletarMateria(selecionada.getId());

            exibirMensagem("Matéria excluída com sucesso!", Color.GREEN);
            carregarMateria();
            btnLimparAction(null);
        }
    }

    @FXML
    public void btnAlterarAction(ActionEvent event){
        MateriaDTO selecionada = tblMateria.getSelectionModel().getSelectedItem();

        if (selecionada != null) {
            if (!validarCampos()) return; // UX: Validação de campos vazios

            MateriaDTO objMateriaDTO = new MateriaDTO();
            objMateriaDTO.setId(selecionada.getId());
            objMateriaDTO.setNome(txtNome.getText());
            objMateriaDTO.setProfessor(txtProfessor.getText());
            objMateriaDTO.setNotaMedia(Double.parseDouble(txtNotaMedia.getText()));
            objMateriaDTO.setAulasSemana(Integer.parseInt(txtAulasSemana.getText()));

            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.alterarMateria(objMateriaDTO);

            exibirMensagem("Matéria alterada com sucesso!", Color.GREEN);
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

            // UX: Desabilita o Salvar durante edição para evitar cadastros acidentais
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

        // UI: Expande as colunas para preencher 100% da largura da tabela
        tblMateria.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // UX: Impede que o usuário digite letras onde só vão números
        restringirEntradaNumerica(txtNotaMedia);
        restringirEntradaNumerica(txtAulasSemana);

        // UX: Botões Excluir e Alterar só ficam ativos se tiver algo selecionado na tabela
        btnExcluir.disableProperty().bind(tblMateria.getSelectionModel().selectedItemProperty().isNull());
        btnAlterar.disableProperty().bind(tblMateria.getSelectionModel().selectedItemProperty().isNull());

        carregarMateria();
    }

    // ================= MÉTODOS AUXILIARES (Boas Práticas de MVC/Código Limpo) ================= //

    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty() || txtProfessor.getText().trim().isEmpty() ||
            txtNotaMedia.getText().trim().isEmpty() || txtAulasSemana.getText().trim().isEmpty()) {
            
            exibirMensagem("Atenção: Preencha todos os campos obrigatórios!", Color.RED);
            return false;
        }
        return true;
    }

    private void exibirMensagem(String texto, Color cor) {
        if (lblMensagem != null) {
            lblMensagem.setText(texto);
            lblMensagem.setTextFill(cor);
        }
    }

    private void restringirEntradaNumerica(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove tudo que não for número (0-9) ou ponto (.)
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                campo.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }
}