package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.producers.NotaProducer;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public void save(NotaMessage notaMessage) {
        //para cada nota da lista de notas criar uma nova nota
        List<Nota> notas = notaMessage.getValor().stream().map(nota -> new Nota(null,notaMessage.getAlunoMatricula(), notaMessage.getDisciplinaId(), nota)).collect(Collectors.toList());
        notaRepository.saveAll(notas);
    }

}
