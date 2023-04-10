package br.ufpe.cin.aps.academicservice.services;

import br.ufpe.cin.aps.academicservice.models.Disciplina;
import br.ufpe.cin.aps.academicservice.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina save(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina update(Long id, Disciplina updatedDisciplina) {
        Disciplina disciplina = findById(id);
        disciplina.setNome(updatedDisciplina.getNome());
        disciplina.setProfessor(updatedDisciplina.getProfessor());
        disciplina.setTurmas(updatedDisciplina.getTurmas());
        return disciplinaRepository.save(disciplina);
    }

    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina not found"));
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }
}
