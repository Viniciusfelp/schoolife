package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaMessage;

import br.ufpe.cin.aps.notafrequenciaservice.repositories.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    public void save(FrequenciaMessage frequenciaMessage) {
        Frequencia frequencia = new Frequencia(null, frequenciaMessage.getAlunoMatricula(), frequenciaMessage.getDisciplinaId(), frequenciaMessage.getData(), frequenciaMessage.isPresente());
        frequenciaRepository.save(frequencia);
    }


}
