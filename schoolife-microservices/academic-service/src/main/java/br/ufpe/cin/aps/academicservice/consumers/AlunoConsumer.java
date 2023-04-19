package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.*;
import br.ufpe.cin.aps.academicservice.models.NotaMessage;
import br.ufpe.cin.aps.academicservice.services.AlunoService;
import br.ufpe.cin.aps.academicservice.services.DisciplinaService;
import br.ufpe.cin.aps.academicservice.services.TurmaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AlunoConsumer {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @RabbitListener(queues = "aluno.create")
    public void consumeCreateAlunoMessage(Aluno aluno) {
        alunoService.save(aluno);
    }

    @RabbitListener(queues = "aluno.update")
    public void consumeUpdateAlunoMessage(String matricula, Aluno aluno) {
        alunoService.update(matricula, aluno);
    }

    @RabbitListener(queues = "aluno.delete")
    public void consumeDeleteAlunoMessage(String alunoId) {
        alunoService.delete(alunoId);
    }

    @RabbitListener(queues = "response.notas.queue")
    public CompletableFuture<AlunoNotasDTO> handleNotaResponse(NotaMessage notaMessage) {
        return CompletableFuture.supplyAsync(() -> {
            Aluno aluno = alunoService.findById(notaMessage.getMatricula());
            Turma turma = turmaService.findById(aluno.getTurma().getId());
            Disciplina disciplina = disciplinaService.findById(notaMessage.getDisciplinaId());

            AlunoNotasDTO alunoNotaDTO = new AlunoNotasDTO();
            alunoNotaDTO.setNome(aluno.getNome());
            alunoNotaDTO.setTurma(turma.getNome());
            alunoNotaDTO.setDisciplina(disciplina.getNome());
            alunoNotaDTO.setNotas(notaMessage.getNotas());

            return alunoNotaDTO;
        });
    }




}
