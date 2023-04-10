package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Professor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateProfessorMessage(Professor professor) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.create", professor);
    }

    public void sendUpdateProfessorMessage(String matricula, Professor professor) {
        professor.setMatricula(matricula);
        rabbitTemplate.convertAndSend("professor.exchange", "professor.update", professor);
    }

    public void sendDeleteProfessorMessage(String matricula) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.delete", matricula);
    }
}
