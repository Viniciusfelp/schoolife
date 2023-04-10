package com.aps.turmaservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "anoLetivo", nullable = false)
    private String anoLetivo;

    @ElementCollection
    @CollectionTable(name = "turma_disciplina", joinColumns = @JoinColumn(name = "turma_id"))
    @Column(name = "disciplina_id")
    private Set<Long> disciplinas = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "turma_aluno", joinColumns = @JoinColumn(name = "turma_id"))
    @Column(name = "aluno_id")
    private Set<Long> alunos = new HashSet<>();

}
