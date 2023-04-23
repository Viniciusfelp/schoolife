package br.ufpe.cin.aps.notafrequenciaservice.controllers;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.services.FrequenciaService;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/frequencias")
public class FrequenciaController {

    @Autowired
    private FrequenciaService frequenciaService;

    @PostMapping
    public ResponseEntity<FrequenciaDTO> registrarFrequencia(@RequestBody Frequencia frequencia) {
        FrequenciaDTO novaFrequencia = frequenciaService.save(frequencia);
        return new ResponseEntity<>(novaFrequencia, HttpStatus.CREATED);
    }

    @GetMapping("/disciplina/{idDisciplina}")
    public ResponseEntity<List<FrequenciaDTO>> frequenciaPorDisciplina(@PathVariable Long idDisciplina) {
        List<FrequenciaDTO> frequencias = frequenciaService.findByDisciplina(idDisciplina);
        return new ResponseEntity<>(frequencias, HttpStatus.OK);
    }

    @GetMapping("/aluno/{matricula}/disciplina/{idDisciplina}")
    public ResponseEntity<List<FrequenciaDTO>> frequenciaPorAlunoEDisciplina(
            @PathVariable String matricula, @PathVariable Long idDisciplina) {
        List<FrequenciaDTO> frequencias = frequenciaService.findByAlunoAndDisciplina(matricula, idDisciplina);
        return new ResponseEntity<>(frequencias, HttpStatus.OK);
    }
    // ...

    @PutMapping("/{id}")
    public ResponseEntity<FrequenciaDTO> atualizarFrequencia(@PathVariable Long id, @RequestBody Frequencia frequencia) {
        try {
            FrequenciaDTO frequenciaAtualizada = frequenciaService.atualizarFrequencia(id, frequencia);
            return new ResponseEntity<>(frequenciaAtualizada, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/aluno/{matricula}/disciplina/{idDisciplina}/data/{data}")
    public ResponseEntity<List<FrequenciaDTO>> buscarFrequenciaPorData(
            @PathVariable String matricula, @PathVariable Long idDisciplina, @PathVariable String data) {
        LocalDate localDate = LocalDate.parse(data);
        List<FrequenciaDTO> frequencias = frequenciaService.findByAlunoDisciplinaAndData(matricula, idDisciplina, localDate);
        return new ResponseEntity<>(frequencias, HttpStatus.OK);
    }

    @GetMapping("/aluno/{matricula}/disciplina/{idDisciplina}/faltas")
    public ResponseEntity<Long> contarFaltas(
            @PathVariable String matricula, @PathVariable Long idDisciplina) {
        Long faltas = frequenciaService.countFaltasByAlunoAndDisciplina(matricula, idDisciplina);
        return new ResponseEntity<>(faltas, HttpStatus.OK);
    }

}
