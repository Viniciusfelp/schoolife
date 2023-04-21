package br.ufpe.cin.aps.academicservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${academic.rabbitmq.exchange}")
    private String exchange;

    // Nota
    @Value("${academic.rabbitmq.queue.nota.buscar}")
    private String buscarNotaQueue;
    @Value("${academic.rabbitmq.queue.nota.buscar_todas}")
    private String buscarTodasNotasQueue;

    // Frequencia
    @Value("${academic.rabbitmq.queue.frequencia.buscar}")
    private String buscarFrequenciaQueue;
    @Value("${academic.rabbitmq.queue.frequencia.buscar_todas}")
    private String buscarTodasFrequenciasQueue;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue buscarNotaQueue() {
        return new Queue(buscarNotaQueue);
    }

    @Bean
    public Queue buscarTodasNotasQueue() {
        return new Queue(buscarTodasNotasQueue);
    }

    @Bean
    public Queue buscarFrequenciaQueue() {
        return new Queue(buscarFrequenciaQueue);
    }

    @Bean
    public Queue buscarTodasFrequenciasQueue() {
        return new Queue(buscarTodasFrequenciasQueue);
    }
}
