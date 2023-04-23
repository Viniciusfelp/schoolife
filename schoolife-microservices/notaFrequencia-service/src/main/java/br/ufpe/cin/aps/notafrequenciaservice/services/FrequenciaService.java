package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.clients.DisciplinaClient;
import br.ufpe.cin.aps.notafrequenciaservice.models.DisciplinaDTO;
import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaDTO;
import br.ufpe.cin.aps.notafrequenciaservice.models.AlunoDTO;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.FrequenciaRepository;
import br.ufpe.cin.aps.notafrequenciaservice.clients.AlunoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private AlunoClient alunoClient;

    @Autowired
    private DisciplinaClient disciplinaClient;

    public FrequenciaDTO save(Frequencia frequencia) {
        Frequencia savedFrequencia = frequenciaRepository.save(frequencia);
        return toDTO(savedFrequencia);
    }

    public List<FrequenciaDTO> findByDisciplina(Long idDisciplina) {
        List<Frequencia> frequencias = frequenciaRepository.findByDisciplinaId(idDisciplina);
        return frequencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<FrequenciaDTO> findByAlunoAndDisciplina(String matricula, Long idDisciplina) {
        List<Frequencia> frequencias = frequenciaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, idDisciplina);
        return frequencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FrequenciaDTO atualizarFrequencia(Long id, Frequencia frequencia) {
        Frequencia frequenciaAtual = frequenciaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        frequenciaAtual.setPresenca(frequencia.getPresenca());
        Frequencia updatedFrequencia = frequenciaRepository.save(frequenciaAtual);
        return toDTO(updatedFrequencia);
    }

    public List<FrequenciaDTO> findByAlunoDisciplinaAndData(String matricula, Long idDisciplina, LocalDate data) {
        List<Frequencia> frequencias = frequenciaRepository.findByAlunoMatriculaAndDisciplinaIdAndData(matricula, idDisciplina, data);
        return frequencias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Long countFaltasByAlunoAndDisciplina(String matricula, Long idDisciplina) {
        return frequenciaRepository.countByAlunoMatriculaAndDisciplinaIdAndPresenca(matricula, idDisciplina, false);
    }

    private AlunoDTO findAlunoDTOByMatricula(String matricula) {
        return alunoClient.getAlunoByMatricula(matricula);
    }

    private DisciplinaDTO findDisciplinaNomeById(Long id) {
        return disciplinaClient.getDisciplinaById(id);
    }

    private FrequenciaDTO toDTO(Frequencia frequencia) {
        return new FrequenciaDTO(
                frequencia.getId(),
                findAlunoDTOByMatricula(frequencia.getAlunoMatricula()),
                findDisciplinaNomeById(frequencia.getDisciplinaId()),
                frequencia.getData(),
                frequencia.getPresenca());
    }
}
