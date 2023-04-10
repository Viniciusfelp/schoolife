package br.ufpe.cin.aps.atividadeextracurricularservice.controllers;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import br.ufpe.cin.aps.atividadeextracurricularservice.producer.AtividadeExtraCurricularProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades-extracurricular")
public class AtividadeExtraCurricularController {

    @Autowired
    private AtividadeExtraCurricularProducer atividadeProducer;

    @PostMapping
    public ResponseEntity<Void> createAtividade(@RequestBody AtividadeExtraCurricular atividade) {
        atividadeProducer.sendCreateAtividadeMessage(atividade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAtividade(@PathVariable Long id, @RequestBody AtividadeExtraCurricular atividade) {
        atividadeProducer.sendUpdateAtividadeMessage(id, atividade);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Long id) {
        atividadeProducer.sendDeleteAtividadeMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{atividadeId}/inscrever/{alunoMatricula}")
    public ResponseEntity<Void> inscreverAluno(@PathVariable String alunoMatricula, @PathVariable Long atividadeId) {
        atividadeProducer.sendInscricaoAlunoMessage(alunoMatricula, atividadeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
