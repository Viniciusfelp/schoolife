package com.aps.schoolife.controllers;

import com.aps.schoolife.models.RegistroAcademico;
import com.aps.schoolife.services.RegistroAcademicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class RegistroAcademicoController {
    @Autowired
    private RegistroAcademicoService registroAcademicoService;

    @PostMapping
    public RegistroAcademico cadastrarNota(@RequestBody RegistroAcademico registroAcademico) {
        return registroAcademicoService.salvar(registroAcademico);
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
    public ResponseEntity<RegistroAcademico> atualizarNota(@PathVariable Long notaId, @RequestBody RegistroAcademico registroAcademicoAtualizada) {
        RegistroAcademico registroAcademico = registroAcademicoService.buscarNotaPorId(notaId);
        registroAcademico.setNota(registroAcademicoAtualizada.getNota());
        RegistroAcademico registroAcademicoAtualizadaAux = registroAcademicoService.atualizarNota(registroAcademico);
        return ResponseEntity.ok(registroAcademicoAtualizadaAux);
    }

    @DeleteMapping("/notas/{notaId}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long notaId) {
        registroAcademicoService.deletarNota(notaId);
        return ResponseEntity.noContent().build();
    }

}
