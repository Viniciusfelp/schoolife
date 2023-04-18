package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import br.ufpe.cin.aps.academicservice.models.Frequencia;
import br.ufpe.cin.aps.academicservice.models.Nota;
import br.ufpe.cin.aps.academicservice.producers.AlunoProducer;
import br.ufpe.cin.aps.academicservice.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoProducer alunoProducer;

    @Autowired
    private AlunoService alunoService;

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