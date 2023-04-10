package com.aps.alunoservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(name = "data_de_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "data_de_ingresso", nullable = false)
    private LocalDate dataDeIngresso;

    @Column(name = "turma_id", nullable = false)
    private Long turmaId;
}
