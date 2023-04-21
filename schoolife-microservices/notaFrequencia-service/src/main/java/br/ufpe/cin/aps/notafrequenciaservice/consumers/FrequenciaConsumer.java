package br.ufpe.cin.aps.notafrequenciaservice.consumers;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.services.FrequenciaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FrequenciaConsumer {
    @Autowired
    private FrequenciaService frequenciaService;


    @RabbitListener(queues = "${rabbitmq.queue.frequencias}")
    public void receiveFrequencia(FrequenciaMessage frequencia) {
        frequenciaService.addFrequencia(frequencia);
    }
}


