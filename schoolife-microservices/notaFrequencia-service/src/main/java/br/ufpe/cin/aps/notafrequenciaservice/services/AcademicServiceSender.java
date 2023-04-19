package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AcademicServiceSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.notas}")
    private String notasQueueName;

    @Value("${rabbitmq.queue.frequencias}")
    private String frequenciasQueueName;

    public void sendNota(NotaMessage notaMessage) {
        rabbitTemplate.convertAndSend(notasQueueName, notaMessage);
    }

    public void sendFrequencia(Frequencia frequencia) {
        rabbitTemplate.convertAndSend(frequenciasQueueName, frequencia);
    }
}
