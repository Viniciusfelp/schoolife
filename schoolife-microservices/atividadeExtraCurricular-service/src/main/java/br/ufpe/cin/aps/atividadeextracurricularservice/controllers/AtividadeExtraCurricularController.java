package br.ufpe.cin.aps.atividadeextracurricularservice.controllers;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricularDTO;
import br.ufpe.cin.aps.atividadeextracurricularservice.services.AtividadeExtraCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades-extracurricular")
public class AtividadeExtraCurricularController {

    @Autowired
    private AtividadeExtraCurricularService atividadeService;

    @PostMapping
    public ResponseEntity<AtividadeExtraCurricular> createAtividade(@RequestBody AtividadeExtraCurricular atividade) {
        return new ResponseEntity<>(atividadeService.save(atividade), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AtividadeExtraCurricularDTO>> getAllAtividades() {
        return new ResponseEntity<>(atividadeService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeExtraCurricular> updateAtividade(@PathVariable Long id, @RequestBody AtividadeExtraCurricular atividade) {
        return new ResponseEntity<>(atividadeService.update(id, atividade), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{atividadeId}/inscrever/{alunoMatricula}")
    public ResponseEntity<Void> inscreverAluno(@PathVariable String alunoMatricula, @PathVariable Long atividadeId) {
        atividadeService.inscreverAluno(alunoMatricula, atividadeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}