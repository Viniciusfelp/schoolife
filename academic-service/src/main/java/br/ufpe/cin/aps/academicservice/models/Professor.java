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
@Table(name = "professores")
public class Professor {
    @Id
    private String matricula;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private Set<Disciplina> disciplinas = new HashSet<>();
}
