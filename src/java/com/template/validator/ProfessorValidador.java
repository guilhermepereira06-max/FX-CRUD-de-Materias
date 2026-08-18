package com.template.validator;

import java.util.regex.Pattern;

public class ProfessorValidador implements Validador<String> {
    private  static final String PROFESSOR_REGEX=".*[0-9].*";
    private final Pattern pattern = Pattern.compile(PROFESSOR_REGEX);
    private final String professor;

    public ProfessorValidador(String professor){
        this.professor=professor;
    }

    @Override
    public boolean validar(String valorAtual){
        return this.professor != null && pattern.matcher(this.professor).matches();
    }

    @Override
    public String getMensagemErro(){
        return"Digite um nome de professor sem numeros";
    }

    @Override
    public String getValor(){
        return professor;
    }
}

