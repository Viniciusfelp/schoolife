package com.aps.professorservice.services;

import com.aps.professorservice.models.Professor;
import com.aps.professorservice.producer.ProfessorProducer;
import com.aps.professorservice.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorProducer professorProducer;

    public Professor save(Professor professor) {
        Professor savedProfessor = professorRepository.save(professor);
        professorProducer.sendCreateProfessorMessage(savedProfessor);
        return savedProfessor;
    }

    public Professor update(String id, Professor updatedProfessor) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setNome(updatedProfessor.getNome());
            professor.setDisciplinas(updatedProfessor.getDisciplinas());
            Professor updated = professorRepository.save(professor);
            professorProducer.sendUpdateProfessorMessage(id, updated);
            return updated;
        } else {
            throw new RuntimeException("Professor not found");
        }
    }

    public void delete(String id) {
        professorRepository.deleteById(id);
        professorProducer.sendDeleteProfessorMessage(id);
    }

    public Professor findById(String id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor addDisciplina(String professorId, Long disciplinaId) {
        Professor professor = findById(professorId);
        professor.getDisciplinas().add(disciplinaId);
        Professor updated = professorRepository.save(professor);
        professorProducer.sendAddDisciplinaMessage(professorId, disciplinaId);
        return updated;
    }

    public void removeDisciplina(String professorId, Long disciplinaId) {
        Professor professor = findById(professorId);
        professor.getDisciplinas().remove(disciplinaId);
        professorRepository.save(professor);
        professorProducer.sendRemoveDisciplinaMessage(professorId, disciplinaId);
    }
}
