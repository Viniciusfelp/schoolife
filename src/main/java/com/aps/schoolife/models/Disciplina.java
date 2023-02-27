package com.aps.schoolife.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
    @Id
    @Column(name = "codigo")
    private String codigo;
    private String nome;
    @ManyToMany
    @JoinTable(name = "disciplina_turma",
            joinColumns = @JoinColumn(name = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private List<Turma> turma;
    @ManyToOne
    @JoinColumn(name = "cpf")
    private Professor professor;
}
