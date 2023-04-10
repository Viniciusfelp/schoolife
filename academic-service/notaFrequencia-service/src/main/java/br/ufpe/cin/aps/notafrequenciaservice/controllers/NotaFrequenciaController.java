package br.ufpe.cin.aps.notafrequenciaservice.controllers;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaFrequencia;
import br.ufpe.cin.aps.notafrequenciaservice.producers.NotaFrequenciaProducer;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaFrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notafrequencia")
public class NotaFrequenciaController {

    @Autowired
    private NotaFrequenciaProducer notaFrequenciaProducer;

    @Autowired
    private NotaFrequenciaService notaFrequenciaService;
    @PostMapping
    public ResponseEntity<Void> createNotaFrequencia(@RequestBody NotaFrequencia notaFrequencia) {
        notaFrequenciaProducer.sendCreateNotaFrequenciaMessage(notaFrequencia);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotaFrequencia(@PathVariable Long id, @RequestBody NotaFrequencia notaFrequencia) {
        notaFrequenciaProducer.sendUpdateNotaFrequenciaMessage(id, notaFrequencia);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotaFrequencia(@PathVariable Long id) {
        notaFrequenciaProducer.sendDeleteNotaFrequenciaMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<NotaFrequencia>> getAllNotaFrequencia() {
        return new ResponseEntity<>(notaFrequenciaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaFrequencia> getNotaFrequencia(@PathVariable Long id) {
        return new ResponseEntity<>(notaFrequenciaService.findById(id), HttpStatus.OK);
    }


}
