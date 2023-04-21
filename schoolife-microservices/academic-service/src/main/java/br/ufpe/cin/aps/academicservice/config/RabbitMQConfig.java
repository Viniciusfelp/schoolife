package br.ufpe.cin.aps.academicservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }



    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
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

    // Aluno queues
    @Bean
    public Queue alunoCreateQueue() {
        return new Queue("aluno.create");
    }

    @Bean
    public Queue alunoUpdateQueue() {
        return new Queue("aluno.update");
    }

    @Bean
    public Queue alunoDeleteQueue() {
        return new Queue("aluno.delete");
    }

    // Disciplina queues
    @Bean
    public Queue disciplinaCreateQueue() {
        return new Queue("disciplina.create.queue");
    }

    @Bean
    public Queue disciplinaUpdateQueue() {
        return new Queue("disciplina.update.queue");
    }

    @Bean
    public Queue disciplinaDeleteQueue() {
        return new Queue("disciplina.delete.queue");
    }

    // Professor queues
    @Bean
    public Queue professorCreateQueue() {
        return new Queue("professor.create.queue");
    }

    @Bean
    public Queue professorUpdateQueue() {
        return new Queue("professor.update.queue");
    }

    @Bean
    public Queue professorDeleteQueue() {
        return new Queue("professor.delete.queue");
    }

    // Turma queues
    @Bean
    public Queue turmaCreateQueue() {
        return new Queue("turma.create.queue");
    }

    @Bean
    public Queue turmaUpdateQueue() {
        return new Queue("turma.update.queue");
    }

    @Bean
    public Queue turmaDeleteQueue() {
        return new Queue("turma.delete.queue");
    }
}
