package com.aps.turmaservice.models;
public class TurmaAluno {
    private Long turmaId;
    private Long alunoId;

    public TurmaAluno() {
    }

    public TurmaAluno(Long turmaId, Long alunoId) {
        this.turmaId = turmaId;
        this.alunoId = alunoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }
}
