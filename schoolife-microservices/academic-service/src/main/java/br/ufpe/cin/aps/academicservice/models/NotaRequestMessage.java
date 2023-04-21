package br.ufpe.cin.aps.academicservice.models;

public class NotaRequestMessage {
    private String alunoMatricula;
    private Long disciplinaId;

    public NotaRequestMessage() {
    }

    public NotaRequestMessage(String alunoMatricula, Long disciplinaId) {
        this.alunoMatricula = alunoMatricula;
        this.disciplinaId = disciplinaId;
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
}
