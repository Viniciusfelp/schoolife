package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Disciplina;
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
        disciplina.setId(id);
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.update", disciplina);
    }

    public void sendDeleteDisciplinaMessage(Long id) {
        rabbitTemplate.convertAndSend("disciplina.exchange", "disciplina.delete", id);
    }
}
