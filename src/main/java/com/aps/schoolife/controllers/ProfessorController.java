package com.aps.schoolife.controllers;

import com.aps.schoolife.fachada.Fachada;
import com.aps.schoolife.models.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private Fachada professorService;

    @GetMapping
    public ResponseEntity<Iterable<Professor>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Professor> buscarPorCpf(@PathVariable String cpf) {
        Professor professor = professorService.buscarProfessorPorId(cpf);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor) {
        professor = professorService.cadastrarProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable String cpf, @RequestBody Professor professor) {
        professor.setCpf(cpf);
        professor = professorService.atualizarProfessor(cpf, professor);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> removerProfessor(@PathVariable String cpf) {
        professorService.deletarProfessor(cpf);
        return ResponseEntity.noContent().build();
    }
}

