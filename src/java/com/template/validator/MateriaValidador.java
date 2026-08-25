package com.template.validator;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.*;

public class MateriaValidador {

    //Metodo principal de validação que combina todas as regras
    public static boolean validarMateria(String nome,String professor,String notaMedia,String aulasSemana){
        //Lista de validadores que serao aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        //Adicionando os validadores de campos obrigatorios
        validadores.add(new CampoObrigatorioValidador("Nome",nome));
        validadores.add(new CampoObrigatorioValidador("Professor",professor));
        validadores.add(new CampoObrigatorioValidador("NotaMedia",notaMedia));
        validadores.add(new CampoObrigatorioValidador("AulasSemana",aulasSemana));

        //Adicionando o validador específico de formato de e-mail(aplicado ao campo email)
        validadores.add(new ProfessorValidador(professor));

        //Itera sobre a lista de validadores
        for(Validador<String> validador : validadores){
            //Cada validador testa o seu valor específico
            if(!validador.validar(validador.getValor())){ //O validador agora conhece o valor que vai validar
                mensagemErro(validador.getMensagemErro());//você usuaria DialogUtil.showWarning(mensagem);
                return false;//Retorna falso na primeira falha de validacao
            }
        }
        return true;//Todos os validadores passaram
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