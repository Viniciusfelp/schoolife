package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.Turma;
import br.ufpe.cin.aps.academicservice.services.TurmaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurmaConsumer {

    @Autowired
    private TurmaService turmaService;

    @RabbitListener(queues = "turma.create.queue")
    public void createTurma(Turma turma) {
        turmaService.save(turma);
    }

    @RabbitListener(queues = "turma.update.queue")
    public void updateTurma(Turma turma) {
        turmaService.update(turma.getId(), turma);
    }

    @RabbitListener(queues = "turma.delete.queue")
    public void deleteTurma(Long id) {
        turmaService.delete(id);
    }
}
