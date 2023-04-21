package br.ufpe.cin.aps.atividadeextracurricularservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inscricoes")
public class Inscricao {
    @Id
    @Column(name = "aluno_matricula")
    private String alunoMatricula;

    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private AtividadeExtraCurricular atividade;

}
