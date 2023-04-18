package br.ufpe.cin.aps.academicservice.models;

public class Nota {
    private String matricula;
    private String disciplina;
    private Double nota;

    public Nota() {
    }

    public Nota(String matricula, String disciplina, Double valor) {
        this.matricula = matricula;
        this.disciplina = disciplina;
        this.nota = valor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
