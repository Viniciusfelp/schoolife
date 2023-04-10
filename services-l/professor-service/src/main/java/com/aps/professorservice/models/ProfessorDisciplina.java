package com.aps.professorservice.models;

public class ProfessorDisciplina {
    private String matricula;
    private Long disciplinaId;

    public ProfessorDisciplina(String matricula, Long disciplinaId) {
        this.matricula = matricula;
        this.disciplinaId = disciplinaId;
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

}

