package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.consumers.AlunoConsumer;
import br.ufpe.cin.aps.academicservice.models.*;
import br.ufpe.cin.aps.academicservice.producers.AlunoProducer;
import br.ufpe.cin.aps.academicservice.services.AlunoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoProducer alunoProducer;

    @Autowired
    private AlunoConsumer alunoConsumer;

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{matricula}/disciplinas/{disciplinaId}/notas")
    public ResponseEntity<String> getAlunoNotas(
            @PathVariable("matricula") String matricula,
            @PathVariable("disciplinaId") Long disciplinaId
    ) {
        NotaMessage notaMessage = new NotaMessage(matricula, disciplinaId);

        try {
            CompletableFuture<AlunoNotasDTO> alunoNotasDTOFuture = alunoConsumer.handleNotaResponse(notaMessage);
            AlunoNotasDTO alunoNotasDTO = alunoNotasDTOFuture.get(); // Isso bloqueará até que o resultado esteja disponível

            String json = objectMapper.writeValueAsString(alunoNotasDTO);

            return ResponseEntity.ok(json);
        } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a solicitação");
        }
    }

    @PostMapping
    public ResponseEntity<Void> createAluno(@RequestBody Aluno aluno) {
        alunoProducer.sendCreateAlunoMessage(aluno);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> updateAluno(@PathVariable String matricula, @RequestBody Aluno aluno) {
        alunoProducer.sendUpdateAlunoMessage(matricula, aluno);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String matricula) {
        alunoProducer.sendDeleteAlunoMessage(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> findAlunoById(@PathVariable String matricula) {
        Aluno aluno = alunoService.findById(matricula);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAllAlunos() {
        List<Aluno> alunos = alunoService.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping("/{matricula}/notas")
    public ResponseEntity<Void> adicionarNota(@PathVariable String matricula, @RequestBody Nota nota) {
        nota.setMatricula(matricula);
        alunoService.adicionarNota(nota);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{matricula}/frequencias")
    public ResponseEntity<Void> adicionarFrequencia(@PathVariable String matricula, @RequestBody Frequencia frequencia) {
        frequencia.setMatricula(matricula);
        alunoService.adicionarFrequencia(frequencia);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}