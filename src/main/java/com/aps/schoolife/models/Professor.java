package com.aps.schoolife.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @Column(name = "cpf")
    private String cpf;

    private String nome;
    private String email;
    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplina;

}

