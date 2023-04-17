package br.ufpe.cin.aps.academicservice.services;

import br.ufpe.cin.aps.academicservice.models.Turma;
import br.ufpe.cin.aps.academicservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma save(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma update(Long id, Turma updatedTurma) {
        Turma turma = findById(id);
        turma.setNome(updatedTurma.getNome());
        turma.setAlunos(updatedTurma.getAlunos());
        turma.setDisciplina(updatedTurma.getDisciplina());
        return turmaRepository.save(turma);
    }

    public void delete(Long id) {
        turmaRepository.deleteById(id);
    }

    public Turma findById(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma not found"));
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }
}