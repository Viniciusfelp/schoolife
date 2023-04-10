package com.aps.alunoservice.consumers;

import com.aps.alunoservice.models.Aluno;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AlunoConsumer {
    @RabbitListener(queues = "alunoCreatedQueue")
    public void handleAlunoCreated(Aluno aluno) {
        System.out.println("Aluno criado: " + aluno);
        // Processar a mensagem do aluno criado, conforme necessário
    }

    @RabbitListener(queues = "alunoUpdatedQueue")
    public void handleAlunoUpdated(Aluno aluno) {
        System.out.println("Aluno atualizado: " + aluno);
        // Processar a mensagem do aluno atualizado, conforme necessário
    }

    @RabbitListener(queues = "alunoDeletedQueue")
    public void handleAlunoDeleted(Long alunoId) {
        System.out.println("Aluno excluído com ID: " + alunoId);
        // Processar a mensagem do aluno excluído, conforme necessário
    }
}
