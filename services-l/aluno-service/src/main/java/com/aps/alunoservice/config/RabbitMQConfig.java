package com.aps.alunoservice.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Configuração para aluno criado
    @Bean
    public Queue alunoCreatedQueue() {
        return new Queue("alunoCreatedQueue");
    }

    @Bean
    public DirectExchange alunoCreatedExchange() {
        return new DirectExchange("alunoCreatedExchange");
    }

    @Bean
    public Binding bindingAlunoCreated(Queue alunoCreatedQueue, DirectExchange alunoCreatedExchange) {
        return BindingBuilder.bind(alunoCreatedQueue).to(alunoCreatedExchange).with("aluno.created");
    }

    // Configuração para aluno atualizado
    @Bean
    public Queue alunoUpdatedQueue() {
        return new Queue("alunoUpdatedQueue");
    }

    @Bean
    public DirectExchange alunoUpdatedExchange() {
        return new DirectExchange("alunoUpdatedExchange");
    }

    @Bean
    public Binding bindingAlunoUpdated(Queue alunoUpdatedQueue, DirectExchange alunoUpdatedExchange) {
        return BindingBuilder.bind(alunoUpdatedQueue).to(alunoUpdatedExchange).with("aluno.updated");
    }

    // Configuração para aluno excluído
    @Bean
    public Queue alunoDeletedQueue() {
        return new Queue("alunoDeletedQueue");
    }

    @Bean
    public DirectExchange alunoDeletedExchange() {
        return new DirectExchange("alunoDeletedExchange");
    }

    @Bean
    public Binding bindingAlunoDeleted(Queue alunoDeletedQueue, DirectExchange alunoDeletedExchange) {
        return BindingBuilder.bind(alunoDeletedQueue).to(alunoDeletedExchange).with("aluno.deleted");
    }
}

