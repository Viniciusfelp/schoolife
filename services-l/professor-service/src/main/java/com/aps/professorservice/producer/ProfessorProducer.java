package com.aps.professorservice.producer;

import com.aps.professorservice.models.Professor;
import com.aps.professorservice.models.ProfessorDisciplina;
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
        Professor updatedProfessor = new Professor(professor.getNome(), professor.getEmail());
        updatedProfessor.setMatricula(matricula);
        updatedProfessor.setDisciplinas(professor.getDisciplinas());
        rabbitTemplate.convertAndSend("professor.exchange", "professor.update", updatedProfessor);
    }

    public void sendDeleteProfessorMessage(String matricula) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.delete", matricula);
    }

    public void sendFindProfessorMessage(String matricula) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.find", matricula);
    }

    public void sendFindAllProfessorsMessage() {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.findAll");
    }

    // ...
    public void sendAddDisciplinaMessage(String matricula, Long disciplinaId) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.addDisciplina", new ProfessorDisciplina(matricula, disciplinaId));
    }

    public void sendRemoveDisciplinaMessage(String matricula, Long disciplinaId) {
        rabbitTemplate.convertAndSend("professor.exchange", "professor.removeDisciplina", new ProfessorDisciplina(matricula, disciplinaId));
    }

}
