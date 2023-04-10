package com.aps.disciplinaservice.services;

import com.aps.disciplinaservice.models.Disciplina;
import com.aps.disciplinaservice.producer.DisciplinaProducer;
import com.aps.disciplinaservice.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaProducer disciplinaProducer;

    // CRUD operations

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina save(Disciplina disciplina) {
        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        disciplinaProducer.sendCreateDisciplinaMessage(savedDisciplina);
        return savedDisciplina;
    }

    public void delete(Long id) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(id);
        optionalDisciplina.ifPresent(disciplina -> {
            disciplinaRepository.delete(disciplina);
            disciplinaProducer.sendDeleteDisciplinaMessage(id);
        });
    }

    public Optional<Disciplina> update(Long id, Disciplina disciplina) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplinaToUpdate = optionalDisciplina.get();
            disciplinaToUpdate.setNome(disciplina.getNome());
            disciplinaToUpdate.setDescricao(disciplina.getDescricao());
            disciplinaRepository.save(disciplinaToUpdate);
            disciplinaProducer.sendUpdateDisciplinaMessage(id, disciplinaToUpdate);
            return Optional.of(disciplinaToUpdate);
        }

        return Optional.empty();
    }

    // Adicionar e remover turma

    public boolean addTurma(Long disciplinaId, Long turmaId) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(disciplinaId);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            disciplinaProducer.sendAddTurmaMessage(disciplina, turmaId);
            return true;
        }

        return false;
    }

    public boolean removeTurma(Long disciplinaId, Long turmaId) {
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(disciplinaId);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            disciplinaProducer.sendRemoveTurmaMessage(disciplina, turmaId);
            return true;
        }

        return false;
    }
}
