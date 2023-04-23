package br.ufpe.cin.aps.atividadeextracurricularservice.models;

import java.util.List;

public record AtividadeExtraCurricularDTO(Long id, String nome, String descricao, List<AlunoDTO> alunosInscritos) {
}