package br.ufpe.cin.aps.notafrequenciaservice.producers;

import br.ufpe.cin.aps.notafrequenciaservice.models.*;
import br.ufpe.cin.aps.servicoregistronotafrequencia.models.FrequenciaDiaria;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class NotaFrequenciaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateNotaFrequenciaMessage(NotaFrequencia notaFrequencia) {
        rabbitTemplate.convertAndSend("notafrequencia.exchange", "notafrequencia.create", notaFrequencia);
    }

    public void sendUpdateNotaFrequenciaMessage(Long id, NotaFrequencia notaFrequencia) {
        rabbitTemplate.convertAndSend("notafrequencia.exchange", "notafrequencia.update", notaFrequencia);
    }

    public void sendDeleteNotaFrequenciaMessage(Long id) {
        rabbitTemplate.convertAndSend("notafrequencia.exchange", "notafrequencia.delete", id);
    }


}
