package br.ufpe.cin.aps.notafrequenciaservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.notas}")
    private String notasQueueName;

    @Value("${rabbitmq.queue.frequencias}")
    private String frequenciasQueueName;

    @Bean
    public Queue notasQueue() {
        return new Queue(notasQueueName, true);
    }

    @Bean
    public Queue frequenciasQueue() {
        return new Queue(frequenciasQueueName, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("notafrequencia.exchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("notafrequencia.*");
    }

    @Bean
    public Queue requestNotasQueue() {
        return new Queue("request.notas.queue", true);
    }

    @Bean
    public Queue responseNotasQueue() {
        return new Queue("response.notas.queue", true);
    }

    @Bean
    public DirectExchange requestNotasExchange() {
        return new DirectExchange("request.notas.exchange");
    }

    @Bean
    public DirectExchange responseNotasExchange() {
        return new DirectExchange("response.notas.exchange");
    }

    @Bean
    public Binding requestNotasBinding(Queue requestNotasQueue, DirectExchange requestNotasExchange) {
        return BindingBuilder.bind(requestNotasQueue).to(requestNotasExchange).with("request.notas");
    }

    @Bean
    public Binding responseNotasBinding(Queue responseNotasQueue, DirectExchange responseNotasExchange) {
        return BindingBuilder.bind(responseNotasQueue).to(responseNotasExchange).with("response.notas");
    }

    @Bean
    public Queue requestFrequenciasQueue() {
        return new Queue("request.frequencias.queue", true);
    }

    @Bean
    public Queue responseFrequenciasQueue() {
        return new Queue("response.frequencias.queue", true);
    }

    @Bean
    public DirectExchange requestFrequenciasExchange() {
        return new DirectExchange("request.frequencias.exchange");
    }

    @Bean
    public DirectExchange responseFrequenciasExchange() {
        return new DirectExchange("response.frequencias.exchange");
    }

    @Bean
    public Binding requestFrequenciasBinding(Queue requestFrequenciasQueue, DirectExchange requestFrequenciasExchange) {
        return BindingBuilder.bind(requestFrequenciasQueue).to(requestFrequenciasExchange).with("request.frequencias");
    }

    @Bean
    public Binding responseFrequenciasBinding(Queue responseFrequenciasQueue, DirectExchange responseFrequenciasExchange) {
        return BindingBuilder.bind(responseFrequenciasQueue).to(responseFrequenciasExchange).with("response.frequencias");
    }
}
