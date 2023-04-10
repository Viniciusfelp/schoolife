package com.aps.turmaservice.producers;

import com.aps.turmaservice.models.Turma;
import com.aps.turmaservice.models.TurmaAluno;
import com.aps.turmaservice.models.TurmaDisciplina;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurmaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createTurma(Turma turma) {
        rabbitTemplate.convertAndSend("turmaExchange", "turma.create", turma);
    }

    public void updateTurma(Turma turma) {
        rabbitTemplate.convertAndSend("turmaExchange", "turma.update", turma);
    }

    public void deleteTurma(Long turmaId) {
        rabbitTemplate.convertAndSend("turmaExchange", "turma.delete", turmaId);
    }

    public void addAluno(Long turmaId, Long alunoId) {
        rabbitTemplate.convertAndSend("turmaAlunoExchange", "turma.aluno.adicionar", new TurmaAluno(turmaId, alunoId));
    }

    public void removeAluno(Long turmaId, Long alunoId) {
        rabbitTemplate.convertAndSend("turmaAlunoExchange", "turma.aluno.remover", new TurmaAluno(turmaId, alunoId));
    }

    public void addDisciplina(Long turmaId, Long disciplinaId) {
        rabbitTemplate.convertAndSend("turmaDisciplinaExchange", "turma.disciplina.adicionar", new TurmaDisciplina(turmaId, disciplinaId));
    }

    public void removeDisciplina(Long turmaId, Long disciplinaId) {
        rabbitTemplate.convertAndSend("turmaDisciplinaExchange", "turma.disciplina.remover", new TurmaDisciplina(turmaId, disciplinaId));
    }
}
