package br.ufpe.cin.aps.academicservice.models;

import java.util.List;

public class AlunoNotasDTO {
    private String nome;
    private String matricula;
    private String turma;
    private String disciplina;
    private List<Double> notas;

    public AlunoNotasDTO() {
    }

    public AlunoNotasDTO(String nome, String matricula, String turma, String disciplina, List<Double> notas) {
        this.nome = nome;
        this.matricula = matricula;
        this.turma = turma;
        this.disciplina = disciplina;
        this.notas = notas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
}
