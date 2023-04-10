package br.ufpe.cin.aps.academicservice.services;

import br.ufpe.cin.aps.academicservice.models.Professor;
import br.ufpe.cin.aps.academicservice.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor update(String matricula, Professor updatedProfessor) {
        Professor professor = findById(matricula);
        professor.setNome(updatedProfessor.getNome());
        professor.setEmail(updatedProfessor.getEmail());
        professor.setDisciplinas(updatedProfessor.getDisciplinas());
        return professorRepository.save(professor);
    }

    public void delete(String matricula) {
        professorRepository.deleteById(matricula);
    }

    public Professor findById(String matricula) {
        return professorRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
}
