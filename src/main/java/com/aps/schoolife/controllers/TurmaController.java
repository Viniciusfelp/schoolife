package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Turma;
import com.aps.schoolife.services.DisciplinaService;
import com.aps.schoolife.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/turmas")
    public ResponseEntity<List<Turma>> listarTurmas() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @GetMapping("/turmas/{id}")
    public ResponseEntity<Turma> buscarTurma(@PathVariable Long id) {
        Optional<Turma> turmaOptional = turmaService.buscarTurma(id);
        if(turmaOptional.isPresent()) {
            return ResponseEntity.ok(turmaOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/turmas/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turma) {
        Optional<Turma> turmaOptional = turmaService.buscarTurma(id);
        if(turmaOptional.isPresent()) {
            return ResponseEntity.ok(turmaService.atualizarTurma(id, turma));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/turmas/{id}")
    public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
        Optional<Turma> turmaOptional = turmaService.buscarTurma(id);
        if(turmaOptional.isPresent()) {
            turmaService.deletarTurma(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
