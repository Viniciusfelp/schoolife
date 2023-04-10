package com.aps.professorservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Professor {

    @Id
    private String matricula;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @ElementCollection
    private Set<Long> disciplinas = new HashSet<>();

    public Professor(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.disciplinas = new HashSet<>();
    }
}
