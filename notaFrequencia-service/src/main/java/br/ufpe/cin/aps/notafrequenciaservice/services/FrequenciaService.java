package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.producers.FrequenciaProducer;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private FrequenciaProducer frequenciaProducer;

    public Frequencia save(Frequencia frequencia) {
        return frequenciaRepository.save(frequencia);
    }

    public List<Frequencia> findAll() {
        return frequenciaRepository.findAll();
    }

    public Optional<Frequencia> findById(Long id) {
        return frequenciaRepository.findById(id);
    }

    public void deleteById(Long id) {
        frequenciaRepository.deleteById(id);
    }

    public List<Frequencia> findByAlunoMatricula(String matricula) {
        return frequenciaRepository.findByAlunoMatricula(matricula);
    }

    public List<Frequencia> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId) {
        return frequenciaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId);
    }

    public Frequencia addFrequencia(FrequenciaMessage frequenciaMessage) {
        Frequencia frequencia = new Frequencia();
        frequencia.setAlunoMatricula(frequenciaMessage.getAlunoMatricula());
        frequencia.setDisciplinaId(frequenciaMessage.getDisciplinaId());
        frequencia.setPresenca(frequenciaMessage.isPresente());
        frequencia.setData(frequenciaMessage.getData());
        return save(frequencia);
    }

    public void sendFrequenciaMessage(FrequenciaMessage frequenciaMessage) {
        frequenciaProducer.sendFrequenciaMessage(frequenciaMessage);
    }

    public Frequencia updateFrequencia(Long id, FrequenciaMessage frequenciaMessage) {
        Optional<Frequencia> optionalFrequencia = findById(id);

        if (optionalFrequencia.isPresent()) {
            Frequencia frequencia = optionalFrequencia.get();
            frequencia.setAlunoMatricula(frequenciaMessage.getAlunoMatricula());
            frequencia.setDisciplinaId(frequenciaMessage.getDisciplinaId());
            frequencia.setPresenca(frequenciaMessage.isPresente());
            frequencia.setData(frequenciaMessage.getData());
            return save(frequencia);
        } else {
            throw new RuntimeException("Frequência não encontrada para o ID: " + id);
        }
    }

}
