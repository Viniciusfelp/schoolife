package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.NotaMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class NotaConsumer {

    @Value("${academic.rabbitmq.queue.nota.buscar}")
    private String buscarQueue;

    @Value("${academic.rabbitmq.queue.nota.buscar_todas}")
    private String buscarTodasQueue;

    private CompletableFuture<NotaMessage> buscarNotaFuture;
    private CompletableFuture<List<NotaMessage>> buscarTodasNotasFuture;

    @RabbitListener(queues = "#{buscarQueue}")
    public void handleBuscarNotaResponse(NotaMessage notaMessage) {
        buscarNotaFuture.complete(notaMessage);
    }

    @RabbitListener(queues = "#{buscarTodasQueue}")
    public void handleBuscarTodasNotasResponse(List<NotaMessage> notas) {
        buscarTodasNotasFuture.complete(notas);
    }

    public CompletableFuture<NotaMessage> handleBuscarNotaResponse(Long id) {
        buscarNotaFuture = new CompletableFuture<>();
        return buscarNotaFuture;
    }

    public CompletableFuture<List<NotaMessage>> handleBuscarTodasNotasResponse() {
        buscarTodasNotasFuture = new CompletableFuture<>();
        return buscarTodasNotasFuture;
    }
}
