package br.ufpe.cin.aps.notafrequenciaservice.controllers;

import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/request")
    public void handleNotaRequest(@RequestBody NotaMessage notaMessage) {
        notaService.processNotaRequest(notaMessage);
    }

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(notaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        return notaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nota> createNota(@RequestBody NotaMessage notaMessage) {
        notaService.sendNotaMessage(notaMessage);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{matricula}/{disciplinaId}")
    public ResponseEntity<List<Nota>> updateNota(@PathVariable String matricula, @PathVariable Long disciplinaId, @RequestBody NotaMessage notaMessage) {
        try {
            List<Nota> updatedNotas = notaService.updateNota(matricula, disciplinaId, notaMessage);

            if (updatedNotas == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedNotas);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
