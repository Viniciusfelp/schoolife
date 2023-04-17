package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlunoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateAlunoMessage(Aluno aluno) {
        rabbitTemplate.convertAndSend("aluno.exchange", "aluno.create", aluno);
    }

    public void sendUpdateAlunoMessage(String matricula, Aluno aluno) {
        Aluno alunoPayload = new Aluno(matricula, aluno.getNome(), aluno.getEmail());
        rabbitTemplate.convertAndSend("aluno.exchange", "aluno.update", alunoPayload);
    }

    public void sendDeleteAlunoMessage(String alunoId) {
        rabbitTemplate.convertAndSend("aluno.exchange", "aluno.delete", alunoId);
    }

}
