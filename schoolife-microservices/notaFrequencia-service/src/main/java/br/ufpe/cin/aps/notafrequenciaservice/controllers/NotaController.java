package br.ufpe.cin.aps.notafrequenciaservice.controllers;

import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaDTO;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<Nota> criarNota(@RequestBody Nota nota) {
        return new ResponseEntity<>(notaService.save(nota), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarNota(@PathVariable Long id) {
        return new ResponseEntity<>(notaService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizarNota(@PathVariable Long id, @RequestBody Nota notaAtualizada) {
        return new ResponseEntity<>(notaService.update(id, notaAtualizada), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        notaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/aluno/{matricula}")
    public ResponseEntity<List<NotaDTO>> buscarNotasPorAluno(@PathVariable String matricula) {
        List<NotaDTO> notas = notaService.findNotasWithAlunoAndDisciplinaByAluno(matricula);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<List<NotaDTO>> buscarNotasPorDisciplina(@PathVariable Long idDisciplina) {
        List<NotaDTO> notas = notaService.findNotasWithAlunoAndDisciplinaByDisciplina(idDisciplina);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/aluno/{matricula}/disciplina/{idDisciplina}")
    public ResponseEntity<List<NotaDTO>> buscarNotasPorAlunoEDisciplina(@PathVariable String matricula, @PathVariable Long idDisciplina) {
        List<NotaDTO> notas = notaService.findNotasWithAlunoAndDisciplinaByAlunoAndDisciplina(matricula, idDisciplina);
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }
}
