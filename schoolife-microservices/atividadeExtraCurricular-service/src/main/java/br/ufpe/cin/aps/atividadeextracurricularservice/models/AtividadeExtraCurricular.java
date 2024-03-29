package br.ufpe.cin.aps.atividadeextracurricularservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeExtraCurricular {
    @Id
    private Long id;
    private String nome;
    private String descricao;
    @ManyToMany
    @JoinTable(
            name = "inscricoes",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_matricula")
    )
    private List<Inscricao> alunosInscritos = new ArrayList<>();
}
