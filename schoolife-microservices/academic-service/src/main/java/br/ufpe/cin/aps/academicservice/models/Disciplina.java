package br.ufpe.cin.aps.academicservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_matricula")
    private Professor professor;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private Set<Turma> turmas = new HashSet<>();
}
