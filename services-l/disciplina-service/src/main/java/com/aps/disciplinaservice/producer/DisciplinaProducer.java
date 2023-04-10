package com.aps.disciplinaservice.producer;

import com.aps.disciplinaservice.models.Disciplina;
import com.aps.disciplinaservice.models.DisciplinaTurma;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateDisciplinaMessage(Disciplina disciplina) {
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.create", disciplina);
    }

    public void sendUpdateDisciplinaMessage(Long id, Disciplina disciplina) {
        DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina, id);
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.update", disciplinaTurma);
    }

    public void sendDeleteDisciplinaMessage(Long disciplinaId) {
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.delete", disciplinaId);
    }

    public void sendFindDisciplinaMessage(Long disciplinaId) {
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.find", disciplinaId);
    }

    public void sendAddTurmaMessage(Disciplina disciplina, Long turmaId) {
        DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina, turmaId);
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.addTurma", disciplinaTurma);
    }

    public void sendRemoveTurmaMessage(Disciplina disciplina, Long turmaId) {
        DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina, turmaId);
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.removeTurma", disciplinaTurma);
    }
}
