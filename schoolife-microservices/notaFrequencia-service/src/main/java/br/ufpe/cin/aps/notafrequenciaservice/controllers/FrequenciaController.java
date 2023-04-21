package br.ufpe.cin.aps.notafrequenciaservice.controllers;
import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.FrequenciaService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/frequencias")
public class FrequenciaController {

    @Autowired
    private FrequenciaService frequenciaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.frequencia}")
    private String exchange;

    @Value("${rabbitmq.routingkey.frequencia}")
    private String routingKey;

    @PostMapping
    public ResponseEntity<Frequencia> saveFrequencia(@RequestBody FrequenciaMessage frequenciaMessage) {
        Frequencia frequencia = frequenciaService.addFrequencia(frequenciaMessage);
        rabbitTemplate.convertAndSend(exchange, routingKey, frequenciaMessage);
        return new ResponseEntity<>(frequencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Frequencia>> getAllFrequencias() {
        List<Frequencia> frequencias = frequenciaService.findAll();
        return new ResponseEntity<>(frequencias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frequencia> getFrequenciaById(@PathVariable Long id) {
        Optional<Frequencia> frequencia = frequenciaService.findById(id);
        return frequencia.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Frequencia> updateFrequencia(@PathVariable Long id, @RequestBody FrequenciaMessage frequenciaMessage) {
        Frequencia frequencia = frequenciaService.updateFrequencia(id, frequenciaMessage);
        rabbitTemplate.convertAndSend(exchange, routingKey, frequenciaMessage);
        return new ResponseEntity<>(frequencia, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFrequencia(@PathVariable Long id) {
        frequenciaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

