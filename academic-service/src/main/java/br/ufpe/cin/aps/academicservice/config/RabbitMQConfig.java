package br.ufpe.cin.aps.academicservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.aluno}")
    private String alunoQueue;

    @Value("${rabbitmq.queue.professor}")
    private String professorQueue;

    @Value("${rabbitmq.queue.disciplina}")
    private String disciplinaQueue;

    @Value("${rabbitmq.queue.turma}")
    private String turmaQueue;

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private int port;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Bean
    Queue alunoQueue() {
        return new Queue(alunoQueue, true);
    }

    @Bean
    Queue professorQueue() {
        return new Queue(professorQueue, true);
    }

    @Bean
    Queue disciplinaQueue() {
        return new Queue(disciplinaQueue, true);
    }

    @Bean
    Queue turmaQueue() {
        return new Queue(turmaQueue, true);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
