package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import br.ufpe.cin.aps.academicservice.config.RabbitMQConfig;
import br.ufpe.cin.aps.academicservice.models.Frequencia;
import br.ufpe.cin.aps.academicservice.models.Nota;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlunoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig config;

    @Value("${rabbitmq.queue.notas}")
    private String notasQueue;

    @Value("${rabbitmq.queue.frequencias}")
    private String frequenciasQueue;

    @Value("${rabbitmq.exchange.requestNotas}")
    private String requestNotasExchange;

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




    public void sendNota(Nota nota) {
        rabbitTemplate.convertAndSend(notasQueue, nota);
    }

    public void sendFrequencia(Frequencia frequencia) {
        rabbitTemplate.convertAndSend(frequenciasQueue, frequencia);
    }



}
