package com.aps.schoolife.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
        @Id
        @Column(name = "cpf")
        private String cpf;
        private String nome;
        private String email;
        @ManyToOne
        private Turma turma;

        @ManyToMany(mappedBy = "participantes")
        private List<AtividadeExtraCurricular> atividadesExtracurriculares;



}
