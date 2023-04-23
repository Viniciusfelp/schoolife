package br.ufpe.cin.aps.notafrequenciaservice.clients;

import br.ufpe.cin.aps.notafrequenciaservice.models.DisciplinaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DisciplinaClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${academic-service.url}")
    private String academicServiceUrl;

    public DisciplinaDTO getDisciplinaById(Long id) {
        String url = academicServiceUrl + "/disciplinas/" + id;
        return restTemplate.getForObject(url, DisciplinaDTO.class);
    }
}
