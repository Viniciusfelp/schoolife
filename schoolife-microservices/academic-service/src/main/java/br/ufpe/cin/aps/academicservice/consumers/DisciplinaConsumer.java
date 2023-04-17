package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.Disciplina;
import br.ufpe.cin.aps.academicservice.services.DisciplinaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaConsumer {

    @Autowired
    private DisciplinaService disciplinaService;

    @RabbitListener(queues = "disciplina.create.queue")
    public void createDisciplina(Disciplina disciplina) {
        disciplinaService.save(disciplina);
    }

    @RabbitListener(queues = "disciplina.update.queue")
    public void updateDisciplina(Disciplina disciplina) {
        disciplinaService.update(disciplina.getId(), disciplina);
    }

    @RabbitListener(queues = "disciplina.delete.queue")
    public void deleteDisciplina(Long id) {
        disciplinaService.delete(id);
    }
}
