package br.ufpe.cin.aps.academicservice.producers;

import br.ufpe.cin.aps.academicservice.models.NotaMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotaProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${academic.rabbitmq.exchange}")
    private String exchange;

    @Value("${academic.rabbitmq.routingkey.nota.adicionar}")
    private String adicionarRoutingKey;

    @Value("${academic.rabbitmq.routingkey.nota.atualizar}")
    private String atualizarRoutingKey;

    @Value("${academic.rabbitmq.routingkey.nota.remover}")
    private String removerRoutingKey;

    @Value("${academic.rabbitmq.routingkey.nota.buscar}")
    private String buscarRoutingKey;

    @Value("${academic.rabbitmq.routingkey.nota.buscar_todas}")
    private String buscarTodasRoutingKey;

    public void sendAdicionarNotaMessage(NotaMessage notaMessage) {
        amqpTemplate.convertAndSend(exchange, adicionarRoutingKey, notaMessage);
    }

    public void sendAtualizarNotaMessage(Long id, NotaMessage notaMessage) {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("id", id);
        messageData.put("notaMessage", notaMessage);
        amqpTemplate.convertAndSend(exchange, atualizarRoutingKey, messageData);
    }

    public void sendRemoverNotaMessage(Long id) {
        amqpTemplate.convertAndSend(exchange, removerRoutingKey, id);
    }
    public void sendBuscarNotaMessage(Long id) {
        amqpTemplate.convertAndSend(exchange, buscarRoutingKey, id);
    }

    public void sendBuscarTodasNotasMessage() {
        amqpTemplate.convertAndSend(exchange, buscarTodasRoutingKey, "");
    }

}
