package br.ufpe.cin.aps.notafrequenciaservice.consumers;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaRequestMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaConsumer {

    @Autowired
    private NotaService notaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "notas")
    public void handleMessage(NotaMessage notaMessage) {
        notaService.createNotaFromMessage(notaMessage);
    }

    @RabbitListener(queues = "${rabbitmq.queue.notas}")
    public void receiveNota(NotaMessage nota) {
        notaService.createNotaFromMessage(nota);
    }

    @RabbitListener(queues = "request.notas.queue")
    public void handleNotaRequest(NotaRequestMessage requestMessage) {
        NotaMessage notaMessage = notaService.findNotaByAlunoAndDisciplina(requestMessage.getAlunoMatricula(), requestMessage.getDisciplinaId());
        rabbitTemplate.convertAndSend("response.notas.exchange", "response.notas.routingkey", notaMessage);
    }

}
