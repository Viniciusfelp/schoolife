package com.aps.schoolife.services;

import com.aps.schoolife.models.Disciplina;
import com.aps.schoolife.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public void deletarDisciplina(Long id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
        } else {
            //throw new DisciplinaNaoEncontradaException();
        }
    }

    public Disciplina atualizarDisciplina(Long id, Disciplina disciplina) {
        if (disciplinaRepository.existsById(id)) {
            Disciplina disciplinaAtualizada = disciplinaRepository.getById(id);
            disciplinaAtualizada.setNome(disciplina.getNome());
            disciplinaRepository.save(disciplinaAtualizada);
            return disciplinaAtualizada;
        }else{
            return null;
        }
    }
    
    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }
}
