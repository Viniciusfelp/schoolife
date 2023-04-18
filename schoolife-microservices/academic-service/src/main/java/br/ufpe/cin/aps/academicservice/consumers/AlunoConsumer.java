package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import br.ufpe.cin.aps.academicservice.services.AlunoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlunoConsumer {

    @Autowired
    private AlunoService alunoService;

    @RabbitListener(queues = "aluno.create")
    public void consumeCreateAlunoMessage(Aluno aluno) {
        alunoService.save(aluno);
    }

    @RabbitListener(queues = "aluno.update")
    public void consumeUpdateAlunoMessage(String matricula, Aluno aluno) {
        alunoService.update(matricula, aluno);
    }

    @RabbitListener(queues = "aluno.delete")
    public void consumeDeleteAlunoMessage(String alunoId) {
        alunoService.delete(alunoId);
    }


}
