package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Turma;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurmaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateTurmaMessage(Turma turma) {
        rabbitTemplate.convertAndSend("turma.exchange", "turma.create", turma);
    }

    public void sendUpdateTurmaMessage(Long id, Turma turma) {
        turma.setId(id);
        rabbitTemplate.convertAndSend("turma.exchange", "turma.update", turma);
    }

    public void sendDeleteTurmaMessage(Long id) {
        rabbitTemplate.convertAndSend("turma.exchange", "turma.delete", id);
    }
}
