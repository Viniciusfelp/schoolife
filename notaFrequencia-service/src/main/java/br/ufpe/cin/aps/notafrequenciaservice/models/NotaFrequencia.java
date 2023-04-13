package br.ufpe.cin.aps.notafrequenciaservice.models;

import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaDiaria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notas_frequencias")
public class NotaFrequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String alunoMatricula;

    @NotNull
    private Long disciplinaId;

    @NotNull
    private Double nota;

    @OneToMany(mappedBy = "notaFrequencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FrequenciaDiaria> frequenciasDiarias = new ArrayList<>();

}
