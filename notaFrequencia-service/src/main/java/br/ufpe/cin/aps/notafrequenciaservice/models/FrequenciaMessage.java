package br.ufpe.cin.aps.notafrequenciaservice.models;

import java.io.Serializable;
import java.time.LocalDate;

public class FrequenciaMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alunoMatricula;
    private Long disciplinaId;
    private LocalDate data;
    private boolean presente;

    public FrequenciaMessage(String alunoMatricula, Long disciplinaId, LocalDate data, boolean presente) {
        this.alunoMatricula = alunoMatricula;
        this.disciplinaId = disciplinaId;
        this.data = data;
        this.presente = presente;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(String alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
