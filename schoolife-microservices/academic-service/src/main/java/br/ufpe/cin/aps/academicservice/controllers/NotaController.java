package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.consumers.NotaConsumer;
import br.ufpe.cin.aps.academicservice.models.NotaMessage;
import br.ufpe.cin.aps.academicservice.producers.NotaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaProducer notaProducer;

    @Autowired
    private NotaConsumer notaConsumer;

    @PostMapping
    public ResponseEntity<Void> adicionarNota(@RequestBody NotaMessage notaMessage) {
        notaProducer.sendAdicionarNotaMessage(notaMessage);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarNota(@PathVariable Long id, @RequestBody NotaMessage notaMessage) {
        notaProducer.sendAtualizarNotaMessage(id, notaMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerNota(@PathVariable Long id) {
        notaProducer.sendRemoverNotaMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaMessage> buscarNota(@PathVariable Long id) {
        try {
            notaProducer.sendBuscarNotaMessage(id);
            CompletableFuture<NotaMessage> notaFuture = notaConsumer.handleBuscarNotaResponse(id);
            NotaMessage notaMessage = notaFuture.get();
            return new ResponseEntity<>(notaMessage, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<NotaMessage>> buscarTodasNotas() {
        try {
            notaProducer.sendBuscarTodasNotasMessage();
            CompletableFuture<List<NotaMessage>> notasFuture = notaConsumer.handleBuscarTodasNotasResponse();
            List<NotaMessage> notas = notasFuture.get();
            return new ResponseEntity<>(notas, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
