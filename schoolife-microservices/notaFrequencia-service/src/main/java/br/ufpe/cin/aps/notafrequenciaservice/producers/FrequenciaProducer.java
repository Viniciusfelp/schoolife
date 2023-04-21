package br.ufpe.cin.aps.notafrequenciaservice.producers;

import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FrequenciaProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("requestFrequenciasExchange")
    private DirectExchange frequenciaExchange;

    public void sendFrequenciaMessage(FrequenciaMessage frequenciaMessage) {
        rabbitTemplate.convertAndSend(frequenciaExchange.getName(), "frequencia.routingkey", frequenciaMessage);
    }
}
