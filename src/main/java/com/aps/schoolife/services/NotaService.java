package com.aps.schoolife.services;

import com.aps.schoolife.models.Nota;
import com.aps.schoolife.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota salvar(Nota nota) {
        return notaRepository.save(nota);
    }

    /*public List<Nota> buscarNotasDoAluno(String cpf) {
        return notaRepository.findByAlunoCpf(cpf);
    }

    public List<Nota> buscarNotasDaDisciplina(String id) {
        return notaRepository.findByDisciplinaId(id);
    }

    public List<Nota> buscarNotasDoAlunoEmUmaDisciplina(String cpf, String id) {
        return notaRepository.findByAlunoCpfAndDisciplina(cpf, id);
    }*/

    public void deletarNota(Long notaId) {
        Optional<Nota> nota = notaRepository.findById(notaId);
        if (nota.isPresent()) {
            notaRepository.deleteById(notaId);
        }else{
            //throw new NotaNaoEncontradaException();
        }
    }

    public Nota buscarNotaPorId(Long notaId) {
        Optional<Nota> nota = notaRepository.findById(notaId);
        if (nota.isPresent()) {
            return nota.get();
        } else {
            //throw new NotaNaoEncontradaException();
            return null;
        }
    }

    public Nota atualizarNota(Nota nota) {
        Optional<Nota> notaExistente = notaRepository.findById(nota.getId());
        Nota notaAtualizada = notaExistente.get();
        notaAtualizada.setAluno(nota.getAluno());
        notaAtualizada.setDisciplina(nota.getDisciplina());
        notaAtualizada.setNota(nota.getNota());
        return notaRepository.save(notaAtualizada);
    }
}

