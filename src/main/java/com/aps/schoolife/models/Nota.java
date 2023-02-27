package com.aps.schoolife.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "codigo")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    private Double nota;

    // construtores, getters e setters
}

