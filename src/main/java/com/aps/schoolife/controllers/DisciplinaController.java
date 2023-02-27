package com.aps.schoolife.controllers;

import com.aps.schoolife.models.Disciplina;
import com.aps.schoolife.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    //criar disciplina
    @PostMapping("/disciplinas")
    public ResponseEntity<Disciplina> criarDisciplina(Disciplina disciplina) {
        return ResponseEntity.ok(disciplinaService.cadastrarDisciplina(disciplina));
    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @DeleteMapping("/disciplinas/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
