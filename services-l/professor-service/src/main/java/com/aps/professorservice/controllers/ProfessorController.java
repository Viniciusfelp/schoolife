package com.aps.professorservice.controllers;

import com.aps.professorservice.models.Professor;
import com.aps.professorservice.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        return new ResponseEntity<>(professorService.save(professor), HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable String matricula, @RequestBody Professor professor) {
        return new ResponseEntity<>(professorService.update(matricula, professor), HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String matricula) {
        professorService.delete(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Professor> findProfessorById(@PathVariable String matricula) {
        Optional<Professor> professor = Optional.ofNullable(professorService.findById(matricula));
        return professor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*@GetMapping("/email/{email}")
    public ResponseEntity<Professor> findProfessorByEmail(@PathVariable String email) {
        Optional<Professor> professor = professorService.findByEmail(email);
        return professor.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @GetMapping
    public ResponseEntity<List<Professor>> findAllProfessors() {
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/{matricula}/disciplinas/{disciplinaId}")
    public ResponseEntity<Professor> addDisciplina(@PathVariable String matricula, @PathVariable Long disciplinaId) {
        return new ResponseEntity<>(professorService.addDisciplina(matricula, disciplinaId), HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}/disciplinas/{disciplinaId}")
    public ResponseEntity<Void> removeDisciplina(@PathVariable String matricula, @PathVariable Long disciplinaId) {
        professorService.removeDisciplina(matricula, disciplinaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
