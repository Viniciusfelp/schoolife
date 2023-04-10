package com.aps.turmaservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Filas para adicionar e remover disciplinas
    @Bean
    public Queue turmaDisciplinaAdicionarQueue() {
        return new Queue("turmaDisciplinaAdicionarQueue", false);
    }

    @Bean
    public Queue turmaDisciplinaRemoverQueue() {
        return new Queue("turmaDisciplinaRemoverQueue", false);
    }

    // Filas para adicionar e remover alunos
    @Bean
    public Queue turmaAlunoAdicionarQueue() {
        return new Queue("turmaAlunoAdicionarQueue", false);
    }

    @Bean
    public Queue turmaAlunoRemoverQueue() {
        return new Queue("turmaAlunoRemoverQueue", false);
    }

    // Trocas
    @Bean
    public DirectExchange turmaDisciplinaExchange() {
        return new DirectExchange("turmaDisciplinaExchange");
    }

    @Bean
    public DirectExchange turmaAlunoExchange() {
        return new DirectExchange("turmaAlunoExchange");
    }

    // Associações entre filas e trocas
    @Bean
    public Binding turmaDisciplinaAdicionarBinding(Queue turmaDisciplinaAdicionarQueue, DirectExchange turmaDisciplinaExchange) {
        return BindingBuilder.bind(turmaDisciplinaAdicionarQueue).to(turmaDisciplinaExchange).with("turma.disciplina.adicionar");
    }

    @Bean
    public Binding turmaDisciplinaRemoverBinding(Queue turmaDisciplinaRemoverQueue, DirectExchange turmaDisciplinaExchange) {
        return BindingBuilder.bind(turmaDisciplinaRemoverQueue).to(turmaDisciplinaExchange).with("turma.disciplina.remover");
    }

    @Bean
    public Binding turmaAlunoAdicionarBinding(Queue turmaAlunoAdicionarQueue, DirectExchange turmaAlunoExchange) {
        return BindingBuilder.bind(turmaAlunoAdicionarQueue).to(turmaAlunoExchange).with("turma.aluno.adicionar");
    }

    @Bean
    public Binding turmaAlunoRemoverBinding(Queue turmaAlunoRemoverQueue, DirectExchange turmaAlunoExchange) {
        return BindingBuilder.bind(turmaAlunoRemoverQueue).to(turmaAlunoExchange).with("turma.aluno.remover");
    }
}

