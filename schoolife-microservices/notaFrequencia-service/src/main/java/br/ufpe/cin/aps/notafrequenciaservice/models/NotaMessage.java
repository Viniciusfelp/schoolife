package br.ufpe.cin.aps.notafrequenciaservice.models;

import java.util.List;

public record NotaMessage(String matricula, Long disciplinaId, List<Double> nota) {
    public String getAlunoMatricula() {
        return matricula;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public List<Double> getValor() {
        return nota;
    }
}
