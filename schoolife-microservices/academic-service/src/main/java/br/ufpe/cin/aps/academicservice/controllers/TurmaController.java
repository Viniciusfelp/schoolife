package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.models.Turma;
import br.ufpe.cin.aps.academicservice.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Void> createTurma(@RequestBody Turma turma) {
        turmaService.save(turma);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTurma(@PathVariable Long id, @RequestBody Turma turma) {
        turmaService.update(id, turma);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        turmaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findTurmaById(@PathVariable Long id) {
        Turma turma = turmaService.findById(id);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> findAllTurmas() {
        List<Turma> turmas = turmaService.findAll();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }
}
