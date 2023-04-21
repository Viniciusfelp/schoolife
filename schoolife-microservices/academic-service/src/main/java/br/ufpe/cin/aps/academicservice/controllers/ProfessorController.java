package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.models.Professor;
import br.ufpe.cin.aps.academicservice.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Void> createProfessor(@RequestBody Professor professor) {
        professorService.save(professor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> updateProfessor(@PathVariable String matricula, @RequestBody Professor professor) {
        professorService.update(matricula, professor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String matricula) {
        professorService.delete(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Professor> findProfessorById(@PathVariable String matricula) {
        Professor professor = professorService.findById(matricula);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAllProfessores() {
        List<Professor> professores = professorService.findAll();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }
}
