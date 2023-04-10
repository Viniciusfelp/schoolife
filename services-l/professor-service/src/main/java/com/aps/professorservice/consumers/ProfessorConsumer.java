package com.aps.professorservice.consumers;

import com.aps.professorservice.models.Professor;
import com.aps.professorservice.models.ProfessorDisciplina;
import com.aps.professorservice.services.ProfessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorConsumer {

    @Autowired
    private ProfessorService professorService;

    @RabbitListener(queues = "professor.create")
    public void consumeCreateProfessorMessage(Professor professor) {
        professorService.save(professor);
    }

    @RabbitListener(queues = "professor.update")
    public void consumeUpdateProfessorMessage(Professor professor) {
        professorService.update(professor.getMatricula(), professor);
    }

    @RabbitListener(queues = "professor.delete")
    public void consumeDeleteProfessorMessage(String matricula) {
        professorService.delete(matricula);
    }

    @RabbitListener(queues = "professor.find")
    public void consumeFindProfessorMessage(String matricula) {
        professorService.findById(matricula);
    }

    @RabbitListener(queues = "professor.findAll")
    public void consumeFindAllProfessorsMessage() {
        professorService.findAll();
    }

    @RabbitListener(queues = "professor.addDisciplina")
    public void consumeAddDisciplinaMessage(ProfessorDisciplina professorDisciplina) {
        professorService.addDisciplina(professorDisciplina.getMatricula(), professorDisciplina.getDisciplinaId());
    }

    @RabbitListener(queues = "professor.removeDisciplina")
    public void consumeRemoveDisciplinaMessage(ProfessorDisciplina professorDisciplina) {
        professorService.removeDisciplina(professorDisciplina.getMatricula(), professorDisciplina.getDisciplinaId());
    }
}
