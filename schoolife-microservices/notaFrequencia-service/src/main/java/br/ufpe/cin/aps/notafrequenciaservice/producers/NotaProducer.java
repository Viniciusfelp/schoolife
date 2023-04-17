package br.ufpe.cin.aps.notafrequenciaservice.producers;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotaMessage(NotaMessage notaMessage) {
        rabbitTemplate.convertAndSend("notas", notaMessage);
    }
}
