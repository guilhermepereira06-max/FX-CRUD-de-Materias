package com.template.helper;

import com.template.model.dao.MateriaDAO;
import com.template.model.dto.MateriaDTO;
import static com.template.util.DialogUtil.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import static com.template.carregamento.Carregamento.*;
import static com.template.validator.MateriaValidador.*;

public class MainControllerHelper {

    public static void Salvar (String Nome,String Professor,String NotaMedia,String AulasSemana){
        if (!validarCampos(Nome,Professor,NotaMedia,AulasSemana)) return;

        MateriaDTO objMateriaDTO = new MateriaDTO();
        objMateriaDTO.setNome(Nome);
        objMateriaDTO.setProfessor(Professor);
        objMateriaDTO.setNotaMedia(Double.parseDouble(NotaMedia));
        objMateriaDTO.setAulasSemana(Integer.parseInt(AulasSemana));

        MateriaDAO objMateriaDAO = new MateriaDAO();
        objMateriaDAO.cadastrarMateria(objMateriaDTO);

        mensagemConfirmacao("Matéria salva com sucesso! ");
        //carregarMateria();
        Limpar(null);
    }
    public static void Limpar(ActionEvent event){
        String Nome=null;
        String Professor=null;
        String NotaMedia=null;
        String AulasSemana=null;
        String lblMensagem=null;

        if (event != null && lblMensagem != null) {
            lblMensagem=null;
        }
        mensagemInfo("Os campos estão limpos! ");
    }
    public static void Excluir(int id){
        if (id != 0) {
            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.deletarMateria(id);

            mensagemConfirmacao("Matéria excluída com sucesso!");
            //carregarMateria();
            Limpar(null);
        }
    }
    public static void Alterar(int id){
        String Nome=null;
        String Professor=null;
        String NotaMedia=null;
        String AulasSemana=null;
        if (id != 0) {
            if (!validarCampos(Nome,Professor,NotaMedia,AulasSemana)) return;

            MateriaDTO objMateriaDTO = new MateriaDTO();
            objMateriaDTO.setId(id);
            objMateriaDTO.setNome(Nome);
            objMateriaDTO.setProfessor(Professor);
            objMateriaDTO.setNotaMedia(Double.parseDouble(NotaMedia));
            objMateriaDTO.setAulasSemana(Integer.parseInt(AulasSemana));

            MateriaDAO objMateriaDAO = new MateriaDAO();
            objMateriaDAO.alterarMateria(objMateriaDTO);

            mensagemConfirmacao("Matéria alterada com sucesso!");
            //carregarMateria();
            Limpar(null);
        }
    }
}
