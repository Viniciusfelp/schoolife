package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.Frequencia;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FrequenciaProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${academic.rabbitmq.exchange}")
    private String exchange;

    @Value("${academic.rabbitmq.routingkey.frequencia.adicionar}")
    private String adicionarRoutingKey;

    @Value("${academic.rabbitmq.routingkey.frequencia.atualizar}")
    private String atualizarRoutingKey;

    @Value("${academic.rabbitmq.routingkey.frequencia.remover}")
    private String removerRoutingKey;

    public void sendAdicionarFrequenciaMessage(Frequencia frequencia) {
        amqpTemplate.convertAndSend(exchange, adicionarRoutingKey, frequencia);
    }

    public void sendAtualizarFrequenciaMessage(Long id, Frequencia frequencia) {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("id", id);
        messageData.put("frequencia", frequencia);

        amqpTemplate.convertAndSend(exchange, atualizarRoutingKey, messageData);
    }


    public void sendRemoverFrequenciaMessage(Long id) {
        amqpTemplate.convertAndSend(exchange, removerRoutingKey, id);
    }
}
