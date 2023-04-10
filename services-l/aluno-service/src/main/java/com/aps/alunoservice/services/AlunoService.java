package com.aps.alunoservice.services;

import com.aps.alunoservice.models.Aluno;
import com.aps.alunoservice.producers.AlunoProducer;
import com.aps.alunoservice.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoProducer alunoProducer;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno save(Aluno aluno) {
        //conferir se o aluno já existe
        Optional<Aluno> optionalAluno = alunoRepository.findById(aluno.getMatricula());
        if (optionalAluno.isPresent()) {
            throw new RuntimeException("Aluno já existe");
        } else {
            Aluno savedAluno = alunoRepository.save(aluno);
            alunoProducer.sendAlunoCreatedMessage(savedAluno);
            return savedAluno;
        }
    }
    public Aluno update(String id, Aluno aluno) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno updatedAluno = alunoRepository.save(aluno);
            alunoProducer.sendAlunoUpdatedMessage(updatedAluno);
            return updatedAluno;
        }
        return null;
    }

    public void delete(String id) {
        Optional<Aluno>optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            alunoRepository.deleteById(id);
            alunoProducer.sendAlunoDeletedMessage(id);
        }else{
            throw new RuntimeException("Aluno não encontrado");
        }
    }

    public Aluno findById(String id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        return optionalAluno.orElse(null);
    }
}