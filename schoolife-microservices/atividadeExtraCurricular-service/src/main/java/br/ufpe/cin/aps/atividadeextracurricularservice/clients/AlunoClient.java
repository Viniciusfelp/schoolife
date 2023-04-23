package br.ufpe.cin.aps.atividadeextracurricularservice.clients;

import br.ufpe.cin.aps.atividadeextracurricularservice.models.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlunoClient {

    @Value("${academic-service.url}")
    private String academicServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public AlunoDTO getAluno(String matricula) {
        String url = academicServiceUrl + "/alunos/" + matricula;
        return restTemplate.getForObject(url, AlunoDTO.class);
    }
}
