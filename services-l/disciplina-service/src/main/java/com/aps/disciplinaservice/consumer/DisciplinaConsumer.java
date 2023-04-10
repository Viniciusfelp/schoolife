package com.aps.disciplinaservice.consumer;

import com.aps.disciplinaservice.models.Disciplina;
import com.aps.disciplinaservice.models.DisciplinaTurma;
import com.aps.disciplinaservice.services.DisciplinaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaConsumer {

    @Autowired
    private DisciplinaService disciplinaService;

    @RabbitListener(queues = "disciplina.create")
    public void consumeCreateDisciplinaMessage(Disciplina disciplina) {
        disciplinaService.save(disciplina);
    }

    @RabbitListener(queues = "disciplina.update")
    public void consumeUpdateDisciplinaMessage(DisciplinaTurma disciplinaTurma) {
        disciplinaService.update(disciplinaTurma.getId(), disciplinaTurma.getDisciplina());
    }

    @RabbitListener(queues = "disciplina.delete")
    public void consumeDeleteDisciplinaMessage(Long disciplinaId) {
        disciplinaService.delete(disciplinaId);
    }

    @RabbitListener(queues = "disciplina.find")
    public void consumeFindDisciplinaMessage(Long disciplinaId) {
        disciplinaService.findById(disciplinaId);
    }

    @RabbitListener(queues = "disciplina.findAll")
    public void consumeFindAllDisciplinasMessage() {
        disciplinaService.findAll();
    }

    @RabbitListener(queues = "disciplina.addTurma")
    public void consumeAddTurmaMessage(DisciplinaTurma disciplinaTurma) {
        disciplinaService.addTurma(disciplinaTurma.getId(), disciplinaTurma.getTurmaId());
    }

    @RabbitListener(queues = "disciplina.removeTurma")
    public void consumeRemoveTurmaMessage(DisciplinaTurma disciplinaTurma) {
        disciplinaService.removeTurma(disciplinaTurma.getId(), disciplinaTurma.getTurmaId());
    }
}
