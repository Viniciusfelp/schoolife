package br.ufpe.cin.aps.academicservice.services;

import br.ufpe.cin.aps.academicservice.models.*;
import br.ufpe.cin.aps.academicservice.producers.AlunoProducer;
import br.ufpe.cin.aps.academicservice.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoProducer alunoProducer;


    public void adicionarNota(Nota nota) {
        alunoProducer.sendNota(nota);
    }

    public void adicionarFrequencia(Frequencia frequencia) {
        alunoProducer.sendFrequencia(frequencia);
    }


    public Aluno save(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno update(String matricula, Aluno updatedAluno) {
        Aluno aluno = findById(matricula);
        aluno.setNome(updatedAluno.getNome());
        aluno.setEmail(updatedAluno.getEmail());
        aluno.setTurma(updatedAluno.getTurma());
        return alunoRepository.save(aluno);
    }

    public void delete(String matricula) {
        alunoRepository.deleteById(matricula);
    }

    public Aluno findById(String matricula) {
        return alunoRepository.findById(matricula)
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }
}
