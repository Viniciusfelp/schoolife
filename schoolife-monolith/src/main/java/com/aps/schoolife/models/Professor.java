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
    @Column(name = "cpf" , unique = true)
    private String cpf;

    private String nome;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplina;

}

