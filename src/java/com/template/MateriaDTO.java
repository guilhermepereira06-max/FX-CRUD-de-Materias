package com.template;

public class MateriaDTO {
    private int id;
    private String nome;
    private String professor;
    private double nota_media;
    private int aulas_semana;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double getNotaMedia() {
        return nota_media;
    }

    public void setNotaMedia(double nota_media) {
        this.nota_media = nota_media;
    }

    public int getAulasSemana() {
        return aulas_semana;
    }

    public void setAulasSemana(int aulas_semana) {
        this.aulas_semana = aulas_semana;
    }
    @Override
    public String toString() {
        return "MateriaDTO{" + "id=" + id + ", nome=" + nome + ", professor=" + professor + ", nota_media=" + nota_media + ", aulas_semana=" + aulas_semana + '}';
    }

}