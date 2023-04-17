package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import br.ufpe.cin.aps.notafrequenciaservice.models.NotaMessage;
import br.ufpe.cin.aps.notafrequenciaservice.producers.NotaProducer;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private NotaProducer notaProducer;

    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    public void deleteById(Long id) {
        notaRepository.deleteById(id);
    }

    public List<Nota> findByAlunoMatricula(String matricula) {
        return notaRepository.findByAlunoMatricula(matricula);
    }

    public List<Nota> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId) {
        return notaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId);
    }

    public void createNotaFromMessage(NotaMessage notaMessage) {
        Nota nota = new Nota();
        nota.setAlunoMatricula(notaMessage.getAlunoMatricula());
        nota.setDisciplinaId(notaMessage.getDisciplinaId());
        nota.setValor(notaMessage.getValor());
        save(nota);
    }

    public void sendNotaMessage(NotaMessage notaMessage) {
        notaProducer.sendNotaMessage(notaMessage);
    }

    public Nota updateNota(Long id, NotaMessage notaMessage) {
        Optional<Nota> optionalNota = findById(id);
        if (optionalNota.isPresent()) {
            Nota nota = optionalNota.get();
            nota.setAlunoMatricula(notaMessage.getAlunoMatricula());
            nota.setDisciplinaId(notaMessage.getDisciplinaId());
            nota.setValor(notaMessage.getValor());
            return save(nota);
        }
        return null;
    }
}
