package br.ufpe.cin.aps.notafrequenciaservice.models;

import java.util.List;

public record AlunoDTO(
        String matricula,
        String nome,
        String turmaNome,
        List<DisciplinaDTO> disciplinaNotas
) {}
