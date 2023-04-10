package com.aps.turmaservice.consumers;

import com.aps.turmaservice.models.Turma;
import com.aps.turmaservice.models.TurmaAluno;
import com.aps.turmaservice.models.TurmaDisciplina;
import com.aps.turmaservice.services.TurmaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurmaConsumer {

    @Autowired
    private TurmaService turmaService;

    @RabbitListener(queues = "turmaCreateQueue")
    public void createTurma(Turma turma) {
        turmaService.save(turma);
    }

    @RabbitListener(queues = "turmaUpdateQueue")
    public void updateTurma(Turma turma) {
        turmaService.update(turma.getId(), turma);
    }

    @RabbitListener(queues = "turmaDeleteQueue")
    public void deleteTurma(Long turmaId) {
        turmaService.delete(turmaId);
    }

    @RabbitListener(queues = "turmaAlunoAdicionarQueue")
    public void addAluno(TurmaAluno turmaAluno) {
        turmaService.addAluno(turmaAluno.getTurmaId(), turmaAluno.getAlunoId());
    }

    @RabbitListener(queues = "turmaAlunoRemoverQueue")
    public void removeAluno(TurmaAluno turmaAluno) {
        turmaService.removeAluno(turmaAluno.getTurmaId(), turmaAluno.getAlunoId());
    }

    @RabbitListener(queues = "turmaDisciplinaAdicionarQueue")
    public void addDisciplina(TurmaDisciplina turmaDisciplina) {
        turmaService.addDisciplina(turmaDisciplina.getTurmaId(), turmaDisciplina.getDisciplinaId());
    }

    @RabbitListener(queues = "turmaDisciplinaRemoverQueue")
    public void removeDisciplina(TurmaDisciplina turmaDisciplina) {
        turmaService.removeDisciplina(turmaDisciplina.getTurmaId(), turmaDisciplina.getDisciplinaId());
    }
}
