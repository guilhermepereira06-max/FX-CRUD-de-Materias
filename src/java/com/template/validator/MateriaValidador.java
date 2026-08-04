package com.template.validator;

import com.template.util.DialogUtil;
import javafx.scene.control.TextField;

public class MateriaValidador {
    //Pega o campo do formulario e verifica se este está em branco ou não
    public static boolean validarCampos(String txtNome,String txtProfessor,String txtNotaMedia,String txtAulasSemana) {
        if (txtNome.trim().isEmpty() || txtProfessor.trim().isEmpty() ||
                txtNotaMedia.trim().isEmpty() || txtAulasSemana.trim().isEmpty() ) {

            DialogUtil.mensagemErro("Atenção: Preencha todos os campos obrigatórios!");
            return false;
        }
        return true;
    }

    //Não deixa entrada de palavras ou outros caracteres, permitindo apenas números
    public static void restringirEntradaNumerica(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                campo.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }

}
