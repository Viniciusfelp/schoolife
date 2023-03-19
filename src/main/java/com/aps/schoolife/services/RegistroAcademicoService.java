package com.aps.schoolife.services;

import com.aps.schoolife.models.RegistroAcademico;
import com.aps.schoolife.repository.RegistroAcademicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroAcademicoService {

    @Autowired
    private RegistroAcademicoRepository registroAcademicoRepository;

    public RegistroAcademico salvar(RegistroAcademico registroAcademico) {
        return registroAcademicoRepository.save(registroAcademico);
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
        Optional<RegistroAcademico> nota = registroAcademicoRepository.findById(notaId);
        if (nota.isPresent()) {
            registroAcademicoRepository.deleteById(notaId);
        }else{
            //throw new NotaNaoEncontradaException();
        }
    }

    public RegistroAcademico buscarNotaPorId(Long notaId) {
        Optional<RegistroAcademico> nota = registroAcademicoRepository.findById(notaId);
        if (nota.isPresent()) {
            return nota.get();
        } else {
            //throw new NotaNaoEncontradaException();
            return null;
        }
    }

    public RegistroAcademico atualizarNota(RegistroAcademico registroAcademico) {
        Optional<RegistroAcademico> notaExistente = registroAcademicoRepository.findById(registroAcademico.getId());
        RegistroAcademico registroAcademicoAtualizada = notaExistente.get();
        registroAcademicoAtualizada.setAluno(registroAcademico.getAluno());
        registroAcademicoAtualizada.setDisciplina(registroAcademico.getDisciplina());
        registroAcademicoAtualizada.setNota(registroAcademico.getNota());
        return registroAcademicoRepository.save(registroAcademicoAtualizada);
    }
}

