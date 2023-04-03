package com.aps.schoolife.services;

import com.aps.schoolife.models.Aluno;
import com.aps.schoolife.models.AtividadeExtraCurricular;
import com.aps.schoolife.repository.AlunoRepository;
import com.aps.schoolife.repository.AtividadeExtraCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AtividadeExtraCurricularRepository atividadeExtracurricularRepository;

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
    public Aluno inscreverAlunoEmAtividade(String alunoId, Long atividadeId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        AtividadeExtraCurricular atividade = atividadeExtracurricularRepository.findById(atividadeId).orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        aluno.getAtividadesExtracurriculares().add(atividade);
        atividade.getParticipantes().add(aluno);

        atividadeExtracurricularRepository.save(atividade);

        return aluno;
    }

    public Aluno removerAlunoDaAtividade(String alunoId, Long atividadeId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        AtividadeExtraCurricular atividade = atividadeExtracurricularRepository.findById(atividadeId).orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        aluno.getAtividadesExtracurriculares().remove(atividade);
        atividade.getParticipantes().remove(aluno);

        atividadeExtracurricularRepository.save(atividade);

        return aluno;
    }

}
