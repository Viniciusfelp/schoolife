package br.ufpe.cin.aps.notafrequenciaservice.consumers;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaFrequencia;
import br.ufpe.cin.aps.notafrequenciaservice.services.NotaFrequenciaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotaFrequenciaConsumer {

    @Autowired
    private NotaFrequenciaService notaFrequenciaService;

    @RabbitListener(queues = "notafrequencia.create.queue")
    public void createNotaFrequencia(@Payload NotaFrequencia notaFrequencia) {
        notaFrequenciaService.save(notaFrequencia);
    }

    @RabbitListener(queues = "notafrequencia.update.queue")
    public void updateNotaFrequencia(@Payload NotaFrequencia notaFrequencia) {
        notaFrequenciaService.update(notaFrequencia.getId(), notaFrequencia);
    }

    @RabbitListener(queues = "notafrequencia.delete.queue")
    public void deleteNotaFrequencia(@Payload Long id) {
        notaFrequenciaService.delete(id);
    }
}
