package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Aluno;
import com.aps.schoolife.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

   /* @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return alunoService.listarAlunos();
    }*/

    @GetMapping("/{cpf}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable String cpf) {
        Optional<Aluno> alunoOptional = alunoService.buscarAluno(cpf);

        return alunoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
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
    public ResponseEntity<Void> removerAluno(@PathVariable String cpf) {
        Optional<Aluno> alunoOptional = alunoService.buscarAluno(cpf);

        if (alunoOptional.isPresent()) {
            alunoService.excluirAluno(cpf);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping("/{alunoId}/atividades/{atividadeId}")
    public ResponseEntity<Aluno> inscreverAlunoEmAtividade(@PathVariable String alunoId, @PathVariable Long atividadeId) {
        try {
            Aluno aluno = alunoService.inscreverAlunoEmAtividade(alunoId, atividadeId);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{alunoId}/atividades/{atividadeId}")
    public ResponseEntity<Aluno> removerAlunoDaAtividade(@PathVariable String alunoId, @PathVariable Long atividadeId) {
        try {
            Aluno aluno = alunoService.removerAlunoDaAtividade(alunoId, atividadeId);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

