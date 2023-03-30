package com.aps.schoolife.repository;

import com.aps.schoolife.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    @Query("SELECT COUNT(h) FROM Horario h WHERE h.sala.id = :salaId AND ((h.dataHoraInicio BETWEEN :dataHoraInicio AND :dataHoraFim) OR (h.dataHoraFim BETWEEN :dataHoraInicio AND :dataHoraFim))")
    long countConflitosDeHorario(@Param("salaId") Long salaId, @Param("dataHoraInicio") LocalDateTime dataHoraInicio, @Param("dataHoraFim") LocalDateTime dataHoraFim);
}