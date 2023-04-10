package com.aps.turmaservice.models;

public class TurmaDisciplina {
    private Long turmaId;
    private Long disciplinaId;

    public TurmaDisciplina() {
    }

    public TurmaDisciplina(Long turmaId, Long disciplinaId) {
        this.turmaId = turmaId;
        this.disciplinaId = disciplinaId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
