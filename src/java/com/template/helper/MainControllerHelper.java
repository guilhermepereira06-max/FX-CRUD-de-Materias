package com.template.helper;

import com.template.model.dao.MateriaDAO;
import com.template.model.dto.MateriaDTO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static com.template.carregamento.Carregamento.*;
import static com.template.util.DialogUtil.*;
import static com.template.validator.MateriaValidador.*;

public class MainControllerHelper {

    public static void Salvar(String Nome, String Professor, String NotaMedia, String AulasSemana, TableView<MateriaDTO> tblMateria, TextField txtNome, TextField txtProfessor, TextField txtNotaMedia, TextField txtAulasSemana) {
        if (!validarMateria(Nome, Professor, NotaMedia, AulasSemana)) {
            mensagemErro("Atenção: Preencha todos os campos obrigatórios corretamente!");
            return;
        }

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(Nome);
        objMateriaDTO.setProfessor(Professor);
        objMateriaDTO.setNotaMedia(Double.parseDouble(NotaMedia));
        objMateriaDTO.setAulasSemana(Integer.parseInt(AulasSemana));

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        mensagemConfirmacao("Matéria salva com sucesso!");
        carregarMateria(tblMateria);
        Limpar(txtNome, txtProfessor, txtNotaMedia, txtAulasSemana, tblMateria);
    }

    public static void Limpar(TextField txtNome, TextField txtProfessor, TextField txtNotaMedia, TextField txtAulasSemana, TableView<MateriaDTO> tblMateria) {
        if (txtNome != null) txtNome.clear();
        if (txtProfessor != null) txtProfessor.clear();
        if (txtNotaMedia != null) txtNotaMedia.clear();
        if (txtAulasSemana != null) txtAulasSemana.clear();
        if (tblMateria != null) tblMateria.getSelectionModel().clearSelection();
    }

    public static void Excluir(int id, TableView<MateriaDTO> tblMateria, TextField txtNome, TextField txtProfessor, TextField txtNotaMedia, TextField txtAulasSemana) {
        if (id != 0) {
            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.deletarMateria(id);
            mensagemConfirmacao("Matéria excluída com sucesso!");
            carregarMateria(tblMateria);
            Limpar(txtNome, txtProfessor, txtNotaMedia, txtAulasSemana, tblMateria);
        }
    }

    public static void Alterar(int id, String Nome, String Professor, String NotaMedia, String AulasSemana, TableView<MateriaDTO> tblMateria, TextField txtNome, TextField txtProfessor, TextField txtNotaMedia, TextField txtAulasSemana) {
        if (id != 0) {
            if (!validarMateria(Nome, Professor, NotaMedia, AulasSemana)) {
                mensagemErro("Atenção: Preencha todos os campos obrigatórios corretamente!");
                return;
            }

            MateriaDTO objMateriaDTO = new MateriaDTO();
            objMateriaDTO.setId(id);
            objMateriaDTO.setNome(Nome);
            objMateriaDTO.setProfessor(Professor);
            objMateriaDTO.setNotaMedia(Double.parseDouble(NotaMedia));
            objMateriaDTO.setAulasSemana(Integer.parseInt(AulasSemana));

            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.alterarMateria(objMateriaDTO);

            mensagemConfirmacao("Matéria alterada com sucesso!");
            carregarMateria(tblMateria);
            Limpar(txtNome, txtProfessor, txtNotaMedia, txtAulasSemana, tblMateria);
        }
    }
}