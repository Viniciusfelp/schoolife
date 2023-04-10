package com.aps.alunoservice.controllers;

import com.aps.alunoservice.models.Aluno;

import com.aps.alunoservice.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable String matricula) {
        Aluno aluno = alunoService.findById(matricula);
        if (aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.save(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable String matricula, @RequestBody Aluno aluno) {
        Aluno updatedAluno = alunoService.update(matricula, aluno);
        if (updatedAluno != null) {
            return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String matricula) {
        alunoService.delete(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
