package com.template.validator;

import javafx.scene.control.TextField;
import static com.template.util.DialogUtil.*;

public class MateriaValidador {

    // verifica se os valores numéricos e textos são válidos
    public static boolean validarCampos(String txtNome, String txtProfessor, String txtNotaMedia, String txtAulasSemana) {
        if (txtNome == null || txtNome.trim().isEmpty() || txtProfessor == null || txtProfessor.trim().isEmpty() || txtNotaMedia == null || txtNotaMedia.trim().isEmpty() || txtAulasSemana == null || txtAulasSemana.trim().isEmpty()) {
            return false;
        }

        try { Double.parseDouble(txtNotaMedia);Integer.parseInt(txtAulasSemana);
        } catch (NumberFormatException e) {
            return false;
        }
        if(!validarNome(txtNome)){
            mensagemErro("Digite o nome da materia de 2 a 50 caracteres");
            return false;
        }
        if(!validarProfessor(txtProfessor)){
            mensagemErro("Digite o nome do/da professor/a de 2 a 50 letras");
            return false;
        }
        if(!validarNotaMedia(Double.parseDouble(txtNotaMedia))){
            mensagemErro("A nota deve ser de 0 até 10");
            return false;
        }
        if(!validarAulasSemana(Integer.parseInt(txtAulasSemana))){
            mensagemErro("As aulas não pode ser menores que 0 e maiores que 40");
            return false;
        }
        return true;
    }

    public static boolean validarNome(String nome){
        if(nome.trim().length() < 2 || nome.trim().length() > 50 ){
            return false;
        }
        return true;
    }

    public static boolean validarProfessor(String professor){
        if (professor.matches(".*[0-9].*")) {
            return false;
        }
        if(professor.trim().length() < 2 || professor.trim().length() > 50 ){
            return false;
        }
        return true;
    }

    public static boolean validarNotaMedia(Double notaMedia){
        if((notaMedia < 0.0 || notaMedia > 10.0)){
            return false;
        }
        return true;
    }
    public static boolean validarAulasSemana(int aulasSemana){
        if((aulasSemana< 0 || aulasSemana > 40)){
            return false;
        }
        return true;
    }



    // Não deixa entrada de palavras ou outros caracteres, permitindo apenas números
    public static void restringirEntradaNumerica(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                campo.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }
}