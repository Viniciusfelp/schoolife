package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.AlunoDTO;
import br.ufpe.cin.aps.notafrequenciaservice.models.DisciplinaDTO;
import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaDTO;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${academic-service.url}")
    private String academicServiceBaseUrl;
    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota findById(Long id) {
        Optional<Nota> nota = notaRepository.findById(id);
        return nota.orElse(null);
    }

    public Nota update(Long id, Nota notaAtualizada) {
        Nota nota = findById(id);
        if (nota != null) {
            nota.setValor(notaAtualizada.getValor());
            return notaRepository.save(nota);
        }
        return null;
    }

    public void deleteById(Long id) {
        notaRepository.deleteById(id);
    }

    public List<NotaDTO> findNotasWithAlunoAndDisciplinaByAluno(String matricula) {
        List<Nota> notas = notaRepository.findByAlunoMatricula(matricula);
        return notas.stream().map(this::createNotaDTO).collect(Collectors.toList());
    }

    public List<NotaDTO> findNotasWithAlunoAndDisciplinaByDisciplina(Long idDisciplina) {
        List<Nota> notas = notaRepository.findByDisciplinaId(idDisciplina);
        return notas.stream().map(this::createNotaDTO).collect(Collectors.toList());
    }

    public List<NotaDTO> findNotasWithAlunoAndDisciplinaByAlunoAndDisciplina(String matricula, Long idDisciplina) {
        List<Nota> notas = notaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, idDisciplina);
        return notas.stream().map(this::createNotaDTO).collect(Collectors.toList());
    }

    private NotaDTO createNotaDTO(Nota nota) {
        AlunoDTO aluno = restTemplate.getForObject(academicServiceBaseUrl + "/api/alunos/" + nota.getAlunoMatricula(), AlunoDTO.class);
        DisciplinaDTO disciplina = restTemplate.getForObject(academicServiceBaseUrl + "/api/disciplinas/" + nota.getDisciplinaId(), DisciplinaDTO.class);
        return new NotaDTO(nota.getId(), aluno, disciplina, nota.getValor());
    }

}
