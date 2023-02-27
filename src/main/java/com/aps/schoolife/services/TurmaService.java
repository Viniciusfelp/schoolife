package com.aps.schoolife.services;

import com.aps.schoolife.models.Turma;
import com.aps.schoolife.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public void cadastrarTurma(Turma turma) {
        turmaRepository.save(turma);
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> buscarTurma(Long id) {
        return turmaRepository.findById(id);
    }

    public Turma atualizarTurma(Long id, Turma turma) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isPresent()) {
            Turma turmaAtualizada = turmaOptional.get();
            turmaAtualizada.setNome(turma.getNome());
            turmaAtualizada.setAlunos(turma.getAlunos());
            turmaRepository.save(turmaAtualizada);
            return turmaAtualizada;
        }else{
            return null;
        }
    }

    public void deletarTurma(Long id) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if(turmaOptional.isPresent()) {
            turmaRepository.deleteById(id);
    }else {
            //throw new TurmaNaoEncontradaException();
        }
    }
}
