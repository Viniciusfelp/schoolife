package br.ufpe.cin.aps.atividadeextracurricularservice.services;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import br.ufpe.cin.aps.atividadeextracurricularservice.models.Inscricao;
import br.ufpe.cin.aps.atividadeextracurricularservice.repositories.AtividadeExtraCurricularRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeExtraCurricularService {

    @Autowired
    private AtividadeExtraCurricularRepository atividadeExtraCurricularRepository;

    public AtividadeExtraCurricular save(AtividadeExtraCurricular atividade) {
        return atividadeExtraCurricularRepository.save(atividade);
    }

    public AtividadeExtraCurricular update(Long id, AtividadeExtraCurricular updatedAtividade) {
        AtividadeExtraCurricular atividade = findById(id);
        atividade.setNome(updatedAtividade.getNome());
        atividade.setDescricao(updatedAtividade.getDescricao());
        return atividadeExtraCurricularRepository.save(atividade);
    }

    public void delete(Long id) {
        atividadeExtraCurricularRepository.deleteById(id);
    }

    public AtividadeExtraCurricular findById(Long id) {
        Optional<AtividadeExtraCurricular> atividade = atividadeExtraCurricularRepository.findById(id);
        return atividade.orElseThrow(() -> new RuntimeException("Atividade extra curricular não encontrada"));
    }

    public List<AtividadeExtraCurricular> findAll() {
        return atividadeExtraCurricularRepository.findAll();
    }

    public void inscreverAluno(String alunoMatricula, Long atividadeId) {
        AtividadeExtraCurricular atividade = findById(atividadeId);
        atividade.getAlunosInscritos().add(new Inscricao(alunoMatricula, atividade));
        atividadeExtraCurricularRepository.save(atividade);
    }
}