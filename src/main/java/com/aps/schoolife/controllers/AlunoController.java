package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Aluno;
import com.aps.schoolife.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable String cpf) {
        Optional<Aluno> alunoOptional = alunoService.buscarAluno(cpf);

        if (alunoOptional.isPresent()) {
            return ResponseEntity.ok(alunoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable String cpf, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoService.buscarAluno(cpf);

        if (alunoOptional.isPresent()) {
            Aluno alunoAtualizado = alunoService.atualizarAluno(cpf, aluno);
            return ResponseEntity.ok(alunoAtualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirAluno(@PathVariable String cpf) {
        Optional<Aluno> alunoOptional = alunoService.buscarAluno(cpf);

        if (alunoOptional.isPresent()) {
            alunoService.excluirAluno(cpf);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

