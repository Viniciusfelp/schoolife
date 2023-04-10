package com.aps.turmaservice.controllers;

import com.aps.turmaservice.models.Turma;
import com.aps.turmaservice.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<Turma>> getAllTurmas() {
        return ResponseEntity.ok(turmaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable Long id) {
        return turmaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
        Turma savedTurma = turmaService.save(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@PathVariable Long id, @RequestBody Turma turma) {
        return turmaService.update(id, turma)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-aluno/{alunoId}")
    public ResponseEntity<Void> addAluno(@PathVariable Long id, @PathVariable Long alunoId) {
        if (turmaService.addAluno(id, alunoId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/remove-aluno/{alunoId}")
    public ResponseEntity<Void> removeAluno(@PathVariable Long id, @PathVariable Long alunoId) {
        if (turmaService.removeAluno(id, alunoId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/add-disciplina/{disciplinaId}")
    public ResponseEntity<Void> addDisciplina(@PathVariable Long id, @PathVariable Long disciplinaId) {
        if (turmaService.addDisciplina(id, disciplinaId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/remove-disciplina/{disciplinaId}")
    public ResponseEntity<Void> removeDisciplina(@PathVariable Long id, @PathVariable Long disciplinaId) {
        if (turmaService.removeDisciplina(id, disciplinaId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
