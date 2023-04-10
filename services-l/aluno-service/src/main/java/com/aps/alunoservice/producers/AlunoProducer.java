package com.aps.alunoservice.producers;

import com.aps.alunoservice.models.Aluno;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlunoProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AlunoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAlunoCreatedMessage(Aluno aluno) {
        rabbitTemplate.convertAndSend("alunoCreatedExchange", "aluno.created", aluno);
    }

    public void sendAlunoUpdatedMessage(Aluno aluno) {
        rabbitTemplate.convertAndSend("alunoUpdatedExchange", "aluno.updated", aluno);
    }

    public void sendAlunoDeletedMessage(String alunoId) {
        rabbitTemplate.convertAndSend("alunoDeletedExchange", "aluno.deleted", alunoId);
    }
}
