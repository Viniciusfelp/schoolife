package com.aps.turmaservice.services;

import com.aps.turmaservice.models.Turma;
import com.aps.turmaservice.producers.TurmaProducer;
import com.aps.turmaservice.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaProducer turmaProducer;

    // CRUD operations

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> findById(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma save(Turma turma) {
        Turma savedTurma = turmaRepository.save(turma);
        turmaProducer.createTurma(savedTurma);
        return savedTurma;
    }

    public void delete(Long id) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        optionalTurma.ifPresent(turma -> {
            turmaRepository.delete(turma);
            turmaProducer.deleteTurma(id);
        });
    }

    public Optional<Turma> update(Long id, Turma turma) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {
            Turma turmaToUpdate = optionalTurma.get();
            turmaToUpdate.setNome(turma.getNome());
            turmaToUpdate.setDisciplinas(turma.getDisciplinas());
            turmaToUpdate.setAlunos(turma.getAlunos());
            Turma updatedTurma = turmaRepository.save(turmaToUpdate);
            turmaProducer.updateTurma(updatedTurma);
            return Optional.of(turmaToUpdate);
        }

        return Optional.empty();
    }

    public boolean addAluno(Long id, Long alunoId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getAlunos().add(alunoId);
            turmaRepository.save(turma);
            turmaProducer.addAluno(id, alunoId);
            return true;
        }

        return false;
    }

    public boolean removeAluno(Long id, Long alunoId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getAlunos().remove(alunoId);
            turmaRepository.save(turma);
            turmaProducer.removeAluno(id, alunoId);
            return true;
        }

        return false;
    }

    public boolean addDisciplina(Long id, Long disciplinaId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);

        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getDisciplinas().add(disciplinaId);
            turmaRepository.save(turma);
            turmaProducer.addDisciplina(id, disciplinaId);
            return true;
        }

        return false;
    }

    public boolean removeDisciplina(Long id, Long disciplinaId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getDisciplinas().remove(disciplinaId);
            turmaRepository.save(turma);
            turmaProducer.removeDisciplina(id, disciplinaId);
            return true;
        }

        return false;
    }

// Adicionar e remover disciplinas de uma turma

    /*public Turma adicionarDisciplinaATurma(Long turmaId, Long disciplinaId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(turmaId);

        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getDisciplinas().add(disciplinaId);
            turmaRepository.save(turma);
            turmaProducer.addDisciplina(turmaId, disciplinaId);
            return turma;
        }

        return null;
    }

    public Turma removerDisciplinaDaTurma(Long turmaId, Long disciplinaId) {
        Optional<Turma> optionalTurma = turmaRepository.findById(turmaId);

        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.getDisciplinas().remove(disciplinaId);
            turmaRepository.save(turma);
            turmaProducer.removeDisciplina(turmaId, disciplinaId);
            return turma;
        }

        return null;
    }*/
}