package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.Professor;
import br.ufpe.cin.aps.academicservice.services.ProfessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessorConsumer {

    @Autowired
    private ProfessorService professorService;

    @RabbitListener(queues = "professor.create.queue")
    public void createProfessor(Professor professor) {
        professorService.save(professor);
    }

    @RabbitListener(queues = "professor.update.queue")
    public void updateProfessor(Professor professor) {
        professorService.update(professor.getMatricula(), professor);
    }

    @RabbitListener(queues = "professor.delete.queue")
    public void deleteProfessor(String matricula) {
        professorService.delete(matricula);
    }
}
