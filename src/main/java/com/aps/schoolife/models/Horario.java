package com.aps.schoolife.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private Sala sala;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Horario horario = (Horario) o;
        return id != null && Objects.equals(id, horario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
