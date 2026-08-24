package com.template.validator;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.*;

public class MateriaValidador {

    //Metodo principal de validacao que combina todas as regras
    public boolean validarMateria(String nome,String professor,String notaMedia,String aulasSemana){
        //Lista de validadores que serao aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();


        //Adicionando os validadores de campos obrigatorios
        validadores.add(new CampoObrigatorioValidador("Nome",nome));
        validadores.add(new CampoObrigatorioValidador("Professor",professor));
        validadores.add(new CampoObrigatorioValidador("NotaMedia",notaMedia));
        validadores.add(new CampoObrigatorioValidador("AulasSemana",aulasSemana));

        //Adicionando o validador especifico de formato de e-mail(aplicado ao campo email)
        validadores.add(new ProfessorValidador(professor));

        //Itera sobre a lista de validadores
        for(Validador<String> validador : validadores){
            //Cada validador testa seu valor especifico
            if(!validador.validar(validador.getValor())){ //O validador agora conhece o valor que vai validar
                mensagemErro(validador.getMensagemErro());//você usuaria DialogUtil.showWarning(mensagem);
                return false;//Retorna falso na primeira falha de validacao
            }
        }
        return true;//Todos os validadores passaram
    }

    //verifica se os valores numéricos e textos são válidos
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