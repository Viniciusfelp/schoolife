package com.aps.schoolife.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
        @Id
        private String cpf;
        private String nome;
        private String email;
        @ManyToOne
        @JoinColumn(name = "id_turma")
        private Turma turma;


}
