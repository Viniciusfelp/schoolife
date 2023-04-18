package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import br.ufpe.cin.aps.academicservice.config.RabbitMQConfig;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.directory.DirContext;

@Component
public class AlunoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange exchange;
    @Autowired
    private RabbitMQConfig config;

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

    public void sendAluno(Aluno aluno) {
        rabbitTemplate.convertAndSend(exchange.getName(), "aluno-routing-key", aluno);
    }


}
