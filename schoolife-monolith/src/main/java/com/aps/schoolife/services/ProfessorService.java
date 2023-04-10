package com.aps.schoolife.services;

import com.aps.schoolife.models.Professor;
import com.aps.schoolife.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarProfessorPorId(String cpf) {
        Professor professorAux = null;
        Optional<Professor> professor = professorRepository.findById(cpf);

        if (professor.isPresent()) {
             professorAux = professor.get();
        } else {
            //throw new ProfessorNaoEncontradoException();
        }
        return professorAux;
    }

    public Professor cadastrarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletarProfessor(String id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        } else {
            //throw new ProfessorNaoEncontradoException();
        }
    }

    public Professor atualizarProfessor(String id, Professor professorAtualizado) {
        Optional<Professor> professorExistente = professorRepository.findById(id);

        if (professorExistente.isPresent()) {
            Professor professor = professorExistente.get();
            professor.setNome(professorAtualizado.getNome());
            professor.setCpf(professorAtualizado.getCpf());
            return professorRepository.save(professor);
        } else {
            //throw new ProfessorNaoEncontradoException();
        }
        return professorAtualizado;
    }
}
