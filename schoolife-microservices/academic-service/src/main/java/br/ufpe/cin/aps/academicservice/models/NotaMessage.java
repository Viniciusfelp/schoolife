package br.ufpe.cin.aps.academicservice.models;

import java.io.Serializable;
import java.util.List;

public class NotaMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String matricula;
    private Long disciplinaId;
    private List<Double> notas;

    public NotaMessage() {
    }

    public NotaMessage(String matricula, Long disciplinaId, List<Double> notas) {
        this.matricula = matricula;
        this.disciplinaId = disciplinaId;
        this.notas = notas;
    }

    public NotaMessage(String matricula, Long disciplinaId) {
        this.matricula = matricula;
        this.disciplinaId = disciplinaId;
    }

    // Getters and setters
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

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
}