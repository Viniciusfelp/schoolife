package br.ufpe.cin.aps.academicservice.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class NotaMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
}
