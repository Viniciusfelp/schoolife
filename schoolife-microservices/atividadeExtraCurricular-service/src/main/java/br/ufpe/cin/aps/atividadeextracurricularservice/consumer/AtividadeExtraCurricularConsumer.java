package br.ufpe.cin.aps.atividadeextracurricularservice.consumer;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import br.ufpe.cin.aps.atividadeextracurricularservice.services.AtividadeExtraCurricularService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AtividadeExtraCurricularConsumer {

    @Autowired
    private AtividadeExtraCurricularService atividadeService;

    @RabbitListener(queues = "atividadeextracurricular.create.queue")
    public void processCreateAtividadeMessage(AtividadeExtraCurricular atividade) {
        atividadeService.save(atividade);
    }

    @RabbitListener(queues = "atividadeextracurricular.update.queue")
    public void processUpdateAtividadeMessage(AtividadeExtraCurricular atividade) {
        atividadeService.update(atividade.getId(), atividade);
    }

    @RabbitListener(queues = "atividadeextracurricular.delete.queue")
    public void processDeleteAtividadeMessage(Long id) {
        atividadeService.delete(id);
    }

    @RabbitListener(queues = "atividadeextracurricular.inscricao.queue")
    public void processInscricaoAlunoMessage(Map<String, Object> messagePayload) {
        String alunoMatricula = (String) messagePayload.get("alunoMatricula");
        Long atividadeId = ((Number) messagePayload.get("atividadeId")).longValue();
        atividadeService.inscreverAluno(alunoMatricula, atividadeId);
    }
}
