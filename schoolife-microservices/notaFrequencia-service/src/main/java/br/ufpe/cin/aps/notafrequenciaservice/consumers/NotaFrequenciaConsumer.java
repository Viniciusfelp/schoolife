package br.ufpe.cin.aps.notafrequenciaservice.consumers;

import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.FrequenciaService;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFrequenciaConsumer {

    @Autowired
    private NotaService notaService;

    @Autowired
    private FrequenciaService frequenciaService;

    @RabbitListener(queues = "${notafrequencia.rabbitmq.nota.queue}")
    public void receberMensagemNota(NotaMessage notaMessage) {
        notaService.save(notaMessage);
    }

    @RabbitListener(queues = "${notafrequencia.rabbitmq.frequencia.queue}")
    public void receberMensagemFrequencia(FrequenciaMessage frequenciaMessage) {
        frequenciaService.save(frequenciaMessage);
    }
}