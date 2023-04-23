package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.models.*;
import br.ufpe.cin.aps.academicservice.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Void> createAluno(@RequestBody Aluno aluno) {
        alunoService.save(aluno);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> updateAluno(@PathVariable String matricula, @RequestBody Aluno aluno) {
        alunoService.update(matricula, aluno);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String matricula) {
        alunoService.delete(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> findAlunoById(@PathVariable String matricula) {
        Aluno aluno = alunoService.findById(matricula);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAllAlunos() {
        List<Aluno> alunos = alunoService.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}
