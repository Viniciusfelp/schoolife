package com.aps.disciplinaservice.controllers;

import com.aps.disciplinaservice.models.Disciplina;
import com.aps.disciplinaservice.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Long id) {
        Optional<Disciplina> optionalDisciplina = disciplinaService.findById(id);

        return optionalDisciplina.map(disciplina -> new ResponseEntity<>(disciplina, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        Disciplina createdDisciplina = disciplinaService.save(disciplina);
        return new ResponseEntity<>(createdDisciplina, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        Optional<Disciplina> optionalDisciplina = disciplinaService.update(id, disciplina);

        return optionalDisciplina.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{disciplinaId}/turmas/{turmaId}")
    public ResponseEntity<Void> addTurma(@PathVariable Long disciplinaId, @PathVariable Long turmaId) {
        boolean success = disciplinaService.addTurma(disciplinaId, turmaId);

        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{disciplinaId}/turmas/{turmaId}")
    public ResponseEntity<Void> removeTurma(@PathVariable Long disciplinaId, @PathVariable Long turmaId) {
        boolean success = disciplinaService.removeTurma(disciplinaId, turmaId);

        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
