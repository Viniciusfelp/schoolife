package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Horario;
import com.aps.schoolife.models.Sala;
import com.aps.schoolife.models.Turma;
import com.aps.schoolife.repository.SalaRepository;
import com.aps.schoolife.repository.TurmaRepository;
import com.aps.schoolife.services.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private AlocacaoService alocacaoService;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @PostMapping("/reservar")
    public ResponseEntity<Horario> reservarHorario(
            @RequestParam("turmaId") Long turmaId,
            @RequestParam("salaId") Long salaId,
            @RequestParam("dataHoraInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraInicio,
            @RequestParam("dataHoraFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraFim) {

        Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        Sala sala = salaRepository.findById(salaId).orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        try {
            Horario horarioReservado = alocacaoService.reservarHorario(turma, sala, dataHoraInicio, dataHoraFim);
            return new ResponseEntity<>(horarioReservado, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
