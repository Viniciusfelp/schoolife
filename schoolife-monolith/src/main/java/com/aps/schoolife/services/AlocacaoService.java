package com.aps.schoolife.services;

import com.aps.schoolife.models.Horario;
import com.aps.schoolife.models.Sala;
import com.aps.schoolife.models.Turma;
import com.aps.schoolife.repository.HorarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlocacaoService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Transactional
    public Horario reservarHorario(Turma turma, Sala sala, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        long conflitos = horarioRepository.countConflitosDeHorario(sala.getId(), dataHoraInicio, dataHoraFim);

        if (conflitos > 0) {
            throw new RuntimeException("Horário indisponível para reserva.");
        }

        Horario horario = new Horario();
        horario.setTurma(turma);
        horario.setSala(sala);
        horario.setDataHoraInicio(dataHoraInicio);
        horario.setDataHoraFim(dataHoraFim);

        return horarioRepository.save(horario);
    }

}
