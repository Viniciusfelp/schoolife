package com.aps.schoolife.services;

import com.aps.schoolife.models.Aluno;
import com.aps.schoolife.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarAluno(String cpf) {
        return alunoRepository.findById(cpf);
    }

    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(String cpf, Aluno aluno) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(cpf);
        Aluno alunoAtualizado = alunoExistente.get();
        alunoAtualizado.setNome(aluno.getNome());
        alunoAtualizado.setCpf(aluno.getCpf());
        alunoAtualizado.setTurma(aluno.getTurma());
        return alunoRepository.save(alunoAtualizado);
    }

    public void excluirAluno(String cpf) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(cpf);
        if (alunoExistente.isPresent()) {
            alunoRepository.deleteById(cpf);
        }else{
            //throw new AlunoNaoEncontradoException();
        }
    }
}
