package com.aps.schoolife.controllers;

import com.aps.schoolife.models.AtividadeExtraCurricular;
import com.aps.schoolife.services.AtividadeExtraCurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeExtraCurricularController {

    @Autowired
    private AtividadeExtraCurricularService service;

    @PostMapping
    public ResponseEntity<AtividadeExtraCurricular> create(@RequestBody AtividadeExtraCurricular atividadeExtraCurricular) {
        return new ResponseEntity<>(service.create(atividadeExtraCurricular), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeExtraCurricular> findById(@PathVariable Long id) {
        Optional<AtividadeExtraCurricular> atividade = service.findById(id);
        return atividade.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AtividadeExtraCurricular>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AtividadeExtraCurricular> update(@RequestBody AtividadeExtraCurricular atividadeExtraCurricular) {
        return new ResponseEntity<>(service.update(atividadeExtraCurricular), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

