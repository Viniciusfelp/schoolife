package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.Frequencia;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class FrequenciaConsumer {

    @Value("${academic.rabbitmq.queue.frequencia.buscar}")
    private String buscarQueue;

    @Value("${academic.rabbitmq.queue.frequencia.buscar_todas}")
    private String buscarTodasQueue;

    private CompletableFuture<Frequencia> buscarFrequenciaFuture;
    private CompletableFuture<List<Frequencia>> buscarTodasFrequenciasFuture;

    @RabbitListener(queues = "${academic.rabbitmq.queue.frequencia.buscar}")
    public void handleBuscarFrequenciaResponse(Frequencia frequencia) {
        buscarFrequenciaFuture.complete(frequencia);
    }

    @RabbitListener(queues = "${academic.rabbitmq.queue.frequencia.buscar_todas}")
    public void handleBuscarTodasFrequenciasResponse(List<Frequencia> frequencias) {
        buscarTodasFrequenciasFuture.complete(frequencias);
    }

    public CompletableFuture<Frequencia> handleBuscarFrequenciaResponse(Long id) {
        buscarFrequenciaFuture = new CompletableFuture<>();
        return buscarFrequenciaFuture;
    }

    public CompletableFuture<List<Frequencia>> handleBuscarTodasFrequenciasResponse() {
        buscarTodasFrequenciasFuture = new CompletableFuture<>();
        return buscarTodasFrequenciasFuture;
    }
}
