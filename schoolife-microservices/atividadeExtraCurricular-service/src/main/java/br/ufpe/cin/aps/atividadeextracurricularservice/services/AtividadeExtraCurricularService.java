package br.ufpe.cin.aps.atividadeextracurricularservice.services;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AlunoDTO;
import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricularDTO;
import br.ufpe.cin.aps.atividadeextracurricularservice.models.Inscricao;
import br.ufpe.cin.aps.atividadeextracurricularservice.repositories.AtividadeExtraCurricularRepository;
import br.ufpe.cin.aps.atividadeextracurricularservice.clients.AlunoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AtividadeExtraCurricularService {

    @Autowired
    private AtividadeExtraCurricularRepository atividadeRepository;

    @Autowired
    private AlunoClient alunoClient;

    public AtividadeExtraCurricular save(AtividadeExtraCurricular atividade) {
        return atividadeRepository.save(atividade);
    }

    public List<AtividadeExtraCurricularDTO> findAll() {
        List<AtividadeExtraCurricular> atividades = atividadeRepository.findAll();
        List<AtividadeExtraCurricularDTO> atividadeDTOs = new ArrayList<>();

        for (AtividadeExtraCurricular atividade : atividades) {
            List<AlunoDTO> alunos = new ArrayList<>();
            for (Inscricao inscricao : atividade.getAlunosInscritos()) {
                AlunoDTO alunoDTO = alunoClient.getAluno(inscricao.getAlunoMatricula());
                alunos.add(alunoDTO);
            }
            atividadeDTOs.add(new AtividadeExtraCurricularDTO(atividade.getId(), atividade.getNome(), atividade.getDescricao(), alunos));
        }
        return atividadeDTOs;
    }

    public AtividadeExtraCurricular update(Long id, AtividadeExtraCurricular atividadeAtualizada) {
        Optional<AtividadeExtraCurricular> atividadeExistente = atividadeRepository.findById(id);
        if (atividadeExistente.isEmpty()) {
            throw new NoSuchElementException("Atividade não encontrada");
        }
        AtividadeExtraCurricular atividade = atividadeExistente.get();
        atividade.setNome(atividadeAtualizada.getNome());
        atividade.setDescricao(atividadeAtualizada.getDescricao());
        return atividadeRepository.save(atividade);
    }

    public void deleteById(Long id) {
        atividadeRepository.deleteById(id);
    }

    public void inscreverAluno(String alunoMatricula, Long atividadeId) {
        Optional<AtividadeExtraCurricular> atividadeExistente = atividadeRepository.findById(atividadeId);
        if (atividadeExistente.isEmpty()) {
            throw new NoSuchElementException("Atividade não encontrada");
        }
        AtividadeExtraCurricular atividade = atividadeExistente.get();
        Inscricao inscricao = new Inscricao(alunoMatricula, atividade);
        atividade.getAlunosInscritos().add(inscricao);
        atividadeRepository.save(atividade);
    }
}
