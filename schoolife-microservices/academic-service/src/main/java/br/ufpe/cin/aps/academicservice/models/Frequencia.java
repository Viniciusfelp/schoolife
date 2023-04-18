package br.ufpe.cin.aps.academicservice.models;

public class Frequencia {
    private String matricula;
    private Long disciplinaId;
    private String data;
    private boolean presente;

    public Frequencia(String alunoMatricula, Long disciplinaId, String data, boolean presente) {
        this.matricula = alunoMatricula;
        this.disciplinaId = disciplinaId;
        this.data = data;
        this.presente = presente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
