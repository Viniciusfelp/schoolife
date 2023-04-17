package br.ufpe.cin.aps.notafrequenciaservice.consumers;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaConsumer {

    @Autowired
    private NotaService notaService;

    @RabbitListener(queues = "notas")
    public void handleMessage(NotaMessage notaMessage) {
        notaService.createNotaFromMessage(notaMessage);
    }
}
