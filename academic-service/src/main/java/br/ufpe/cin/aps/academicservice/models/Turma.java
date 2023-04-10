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
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turma_id")
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
}
