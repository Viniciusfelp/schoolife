package br.ufpe.cin.aps.notafrequenciaservice.clients;

import br.ufpe.cin.aps.notafrequenciaservice.models.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlunoClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${academic-service.url}")
    private String academicServiceUrl;

    public AlunoDTO getAlunoByMatricula(String matricula) {
        String url = academicServiceUrl + "/alunos/" + matricula;
        return restTemplate.getForObject(url, AlunoDTO.class);
    }
}
