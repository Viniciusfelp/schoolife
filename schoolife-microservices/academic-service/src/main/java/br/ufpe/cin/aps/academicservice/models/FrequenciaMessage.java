package br.ufpe.cin.aps.academicservice.models;

import java.time.LocalDate;

public class FrequenciaMessage {
    private String matricula;
    private Long disciplinaId;
    private LocalDate data;
    private boolean frequencia;

    public FrequenciaMessage() {
    }

    public FrequenciaMessage(String matricula, Long disciplinaId, LocalDate data, boolean frequencia) {
        this.matricula = matricula;
        this.disciplinaId = disciplinaId;
        this.data = data;
        this.frequencia = frequencia;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isFrequencia() {
        return frequencia;
    }

    public void setFrequencia(boolean frequencia) {
        this.frequencia = frequencia;
    }
}
