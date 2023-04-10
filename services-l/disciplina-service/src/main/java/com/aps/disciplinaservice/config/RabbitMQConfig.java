package com.aps.disciplinaservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("disciplina.exchange");
    }

    @Bean
    public Queue createDisciplinaQueue() {
        return new Queue("disciplina.create");
    }

    @Bean
    public Queue updateDisciplinaQueue() {
        return new Queue("disciplina.update");
    }

    @Bean
    public Queue deleteDisciplinaQueue() {
        return new Queue("disciplina.delete");
    }

    @Bean
    public Queue findDisciplinaQueue() {
        return new Queue("disciplina.find");
    }

    @Bean
    public Queue findAllDisciplinasQueue() {
        return new Queue("disciplina.findAll");
    }

    @Bean
    public Queue addTurmaQueue() {
        return new Queue("disciplina.addTurma");
    }

    @Bean
    public Queue removeTurmaQueue() {
        return new Queue("disciplina.removeTurma");
    }

    @Bean
    public Binding bindingCreateDisciplina(Queue createDisciplinaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(createDisciplinaQueue).to(directExchange).with("disciplina.create");
    }

    @Bean
    public Binding bindingUpdateDisciplina(Queue updateDisciplinaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(updateDisciplinaQueue).to(directExchange).with("disciplina.update");
    }

    @Bean
    public Binding bindingDeleteDisciplina(Queue deleteDisciplinaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(deleteDisciplinaQueue).to(directExchange).with("disciplina.delete");
    }

    @Bean
    public Binding bindingFindDisciplina(Queue findDisciplinaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(findDisciplinaQueue).to(directExchange).with("disciplina.find");
    }

    @Bean
    public Binding bindingFindAllDisciplinas(Queue findAllDisciplinasQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(findAllDisciplinasQueue).to(directExchange).with("disciplina.findAll");
    }

    @Bean
    public Binding bindingAddTurma(Queue addTurmaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(addTurmaQueue).to(directExchange).with("disciplina.addTurma");
    }

    @Bean
    public Binding bindingRemoveTurma(Queue removeTurmaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(removeTurmaQueue).to(directExchange).with("disciplina.removeTurma");
    }
}
