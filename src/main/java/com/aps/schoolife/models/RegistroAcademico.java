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
public class RegistroAcademico {
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
    @OneToMany(mappedBy = "registroAcademico", cascade = CascadeType.ALL)
    private List<Presenca> presencas;
}

