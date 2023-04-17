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

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable Long id, @RequestBody NotaMessage notaMessage) {
        try {
            Nota updatedNota = notaService.updateNota(id, notaMessage);
            return ResponseEntity.ok(updatedNota);
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
