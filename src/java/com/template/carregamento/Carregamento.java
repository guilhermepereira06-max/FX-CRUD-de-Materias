package com.template.carregamento;

import com.template.model.dao.MateriaDAO;
import com.template.model.dto.MateriaDTO;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



import java.util.List;

public class Carregamento {

    public static void carregarCamposMainController(TableView<MateriaDTO> tabela, TextField txtNome, TextField txtProfessor, TextField txtNotaMedia, TextField txtAulasSemana) {

        MateriaDTO objMateriaDTO = tabela.getSelectionModel().getSelectedItem();

        if (objMateriaDTO != null) {
            txtNome.setText(objMateriaDTO.getNome());
            txtProfessor.setText(objMateriaDTO.getProfessor());
            txtNotaMedia.setText(String.valueOf(objMateriaDTO.getNotaMedia()));
            txtAulasSemana.setText(String.valueOf(objMateriaDTO.getAulasSemana()));
        }
    }

    public static void carregarMateria(TableView<MateriaDTO> tblMateria) {
        MateriaDAO objMateriaDAO = new MateriaDAO();
        List<MateriaDTO> listaMateria = objMateriaDAO.listarMaterias();
        tblMateria.setItems(FXCollections.observableArrayList(listaMateria));
    }
}