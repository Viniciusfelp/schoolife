package br.ufpe.cin.aps.atividadeextracurricularservice.producer;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AtividadeExtraCurricular;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AtividadeExtraCurricularProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCreateAtividadeMessage(AtividadeExtraCurricular atividade) {
        rabbitTemplate.convertAndSend("atividadeextracurricular.exchange", "atividade.create", atividade);
    }

    public void sendUpdateAtividadeMessage(Long id, AtividadeExtraCurricular atividade) {
        AtividadeExtraCurricular atividadePayload = new AtividadeExtraCurricular(id, atividade.getNome(), atividade.getDescricao(), atividade.getAlunosInscritos());
        rabbitTemplate.convertAndSend("atividadeextracurricular.exchange", "atividade.update", atividadePayload);
    }

    public void sendDeleteAtividadeMessage(Long id) {
        rabbitTemplate.convertAndSend("atividadeextracurricular.exchange", "atividade.delete", id);
    }

    public void sendInscricaoAlunoMessage(String alunoMatricula, Long atividadeId) {
        Map<String, Object> messagePayload = new HashMap<>();
        messagePayload.put("alunoMatricula", alunoMatricula);
        messagePayload.put("atividadeId", atividadeId);
        rabbitTemplate.convertAndSend("atividadeextracurricular.exchange", "inscricao.create", messagePayload);
    }
}
