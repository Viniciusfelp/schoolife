package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Nota;
import com.aps.schoolife.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public Nota cadastrarNota(@RequestBody Nota nota) {
        return notaService.salvar(nota);
    }

    //Todas as notas de um aluno
    /*@GetMapping("/alunos/{cpf}/notas")
    public ResponseEntity<List<Nota>> buscarNotasDoAluno(@PathVariable String cpf) {
        List<Nota> notas = notaService.buscarNotasDoAluno(cpf);
        return ResponseEntity.ok(notas);
    }
    //Todas as notas de uma disciplina em uma turma
    @GetMapping("/turmas/{id}/disciplinas/{id}/notas")
    public ResponseEntity<List<Nota>> buscarNotasDaDisciplina(@PathVariable String id) {
        List<Nota> notas = notaService.buscarNotasDaDisciplina(id);
        return ResponseEntity.ok(notas);
    }
    //Todas as notas de um aluno em uma disciplina
    @GetMapping("/alunos/{cpf}/disciplinas/{id}/notas")
    public ResponseEntity<List<Nota>> buscarNotasDoAlunoEmUmaDisciplina(@PathVariable String cpf, @PathVariable String id) {
        List<Nota> notas = notaService.buscarNotasDoAlunoEmUmaDisciplina(cpf, id);
        return ResponseEntity.ok(notas);
    }*/
    @PutMapping("/notas/{notaId}")
    public ResponseEntity<Nota> atualizarNota(@PathVariable Long notaId, @RequestBody Nota notaAtualizada) {
        Nota nota = notaService.buscarNotaPorId(notaId);
        nota.setNota(notaAtualizada.getNota());
        Nota notaAtualizadaAux = notaService.atualizarNota(nota);
        return ResponseEntity.ok(notaAtualizadaAux);
    }

    @DeleteMapping("/notas/{notaId}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long notaId) {
        notaService.deletarNota(notaId);
        return ResponseEntity.noContent().build();
    }

}
