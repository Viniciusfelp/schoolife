package com.aps.professorservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue professorCreateQueue() {
        return new Queue("professor.create");
    }

    @Bean
    public Queue professorUpdateQueue() {
        return new Queue("professor.update");
    }

    @Bean
    public Queue professorDeleteQueue() {
        return new Queue("professor.delete");
    }

    @Bean
    public Queue professorFindQueue() {
        return new Queue("professor.find");
    }

    @Bean
    public Queue professorFindAllQueue() {
        return new Queue("professor.findAll");
    }

    @Bean
    public Queue professorAddDisciplinaQueue() {
        return new Queue("professor.addDisciplina");
    }

    @Bean
    public Queue professorRemoveDisciplinaQueue() {
        return new Queue("professor.removeDisciplina");
    }

    @Bean
    public TopicExchange professorExchange() {
        return new TopicExchange("professor.exchange");
    }

    @Bean
    public Binding bindingCreate(Queue professorCreateQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorCreateQueue).to(professorExchange).with("professor.create");
    }

    @Bean
    public Binding bindingUpdate(Queue professorUpdateQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorUpdateQueue).to(professorExchange).with("professor.update");
    }

    @Bean
    public Binding bindingDelete(Queue professorDeleteQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorDeleteQueue).to(professorExchange).with("professor.delete");
    }

    @Bean
    public Binding bindingFind(Queue professorFindQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorFindQueue).to(professorExchange).with("professor.find");
    }

    @Bean
    public Binding bindingFindAll(Queue professorFindAllQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorFindAllQueue).to(professorExchange).with("professor.findAll");
    }

    @Bean
    public Binding bindingAddDisciplina(Queue professorAddDisciplinaQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorAddDisciplinaQueue).to(professorExchange).with("professor.addDisciplina");
    }

    @Bean
    public Binding bindingRemoveDisciplina(Queue professorRemoveDisciplinaQueue, TopicExchange professorExchange) {
        return BindingBuilder.bind(professorRemoveDisciplinaQueue).to(professorExchange).with("professor.removeDisciplina");
    }
}
