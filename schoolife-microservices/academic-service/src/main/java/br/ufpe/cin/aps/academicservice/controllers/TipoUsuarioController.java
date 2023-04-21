package br.ufpe.cin.aps.academicservice.controllers;

import br.ufpe.cin.aps.academicservice.repositories.AlunoRepository;
import br.ufpe.cin.aps.academicservice.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/user-type")
public class TipoUsuarioController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<?> getUserTypeByEmail(@RequestParam("email") String email) {
        if (alunoRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("userType", "aluno"));
        } else if (professorRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("userType", "professor"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}

