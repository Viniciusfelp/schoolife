package br.ufpe.cin.aps.servicoregistronotafrequencia.models;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaFrequencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "frequencias_diarias")
public class FrequenciaDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate data;

    @NotNull
    private Boolean presenca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_frequencia_id")
    private NotaFrequencia notaFrequencia;

}
