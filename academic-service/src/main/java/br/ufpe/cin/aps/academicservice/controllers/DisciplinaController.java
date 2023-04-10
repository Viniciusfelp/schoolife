package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.models.Disciplina;
import br.ufpe.cin.aps.academicservice.producers.DisciplinaProducer;
import br.ufpe.cin.aps.academicservice.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaProducer disciplinaProducer;

    @PostMapping
    public ResponseEntity<Void> createDisciplina(@RequestBody Disciplina disciplina) {
        disciplinaProducer.sendCreateDisciplinaMessage(disciplina);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplinaProducer.sendUpdateDisciplinaMessage(id, disciplina);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        disciplinaProducer.sendDeleteDisciplinaMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findDisciplinaById(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.findById(id);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAllDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }
}