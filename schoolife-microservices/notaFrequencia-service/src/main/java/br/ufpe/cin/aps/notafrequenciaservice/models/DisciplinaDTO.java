package br.ufpe.cin.aps.notafrequenciaservice.models;

public record DisciplinaDTO(
        Long disciplinaId,
        String disciplinaNome,
        Double nota
) {}
