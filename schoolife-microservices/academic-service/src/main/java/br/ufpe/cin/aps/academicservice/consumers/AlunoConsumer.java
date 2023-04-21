package br.ufpe.cin.aps.academicservice.consumers;

import br.ufpe.cin.aps.academicservice.models.*;
import br.ufpe.cin.aps.academicservice.models.NotaMessage;
import br.ufpe.cin.aps.academicservice.producers.AlunoProducer;
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
    private AlunoProducer alunoProducer;

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
    public CompletableFuture<AlunoNotasDTO> handleNotaResponse(NotaRequestMessage notaRequestMessage) {
        return CompletableFuture.supplyAsync(() -> {
            // Enviar a solicitação de notas e receber a resposta
            NotaMessage notaMessage = alunoProducer.requestNotas(notaRequestMessage);

            // Processar a resposta
            if (notaMessage != null) {
                Aluno aluno = alunoService.findById(notaRequestMessage.getAlunoMatricula());
                Turma turma = turmaService.findById(aluno.getTurma().getId());
                Disciplina disciplina = disciplinaService.findById(notaRequestMessage.getDisciplinaId());

                AlunoNotasDTO alunoNotasDTO = new AlunoNotasDTO();
                alunoNotasDTO.setNome(aluno.getNome());
                alunoNotasDTO.setTurma(turma.getNome());
                alunoNotasDTO.setDisciplina(disciplina.getNome());
                alunoNotasDTO.setNotas(notaMessage.getNotas());

                return alunoNotasDTO;
            } else {
                throw new RuntimeException("Não foi possível obter as notas do aluno.");
            }
        });
    }




}
