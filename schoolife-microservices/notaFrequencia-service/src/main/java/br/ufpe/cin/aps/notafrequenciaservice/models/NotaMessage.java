package br.ufpe.cin.aps.notafrequenciaservice.models;

public record NotaMessage(String matricula, Long disciplinaId, Double nota) {
    public String getAlunoMatricula() {
        return matricula;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public Double getValor() {
        return nota;
    }
}
