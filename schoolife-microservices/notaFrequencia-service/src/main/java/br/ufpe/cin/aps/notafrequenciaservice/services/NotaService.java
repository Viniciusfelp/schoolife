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

    @Autowired
    private NotaProducer notaProducer;

    @Autowired
    private AcademicServiceSender academicServiceSender;

    public void processNotaRequest(NotaMessage notaMessage) {

        academicServiceSender.sendNota(notaMessage);
    }

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
        for (Double valor : notaMessage.getValor()) {
            Nota nota = new Nota();
            nota.setAlunoMatricula(notaMessage.getAlunoMatricula());
            nota.setDisciplinaId(notaMessage.getDisciplinaId());
            nota.setValor(valor);
            save(nota);
        }
    }


    public void sendNotaMessage(NotaMessage notaMessage) {
        notaProducer.sendNotaMessage(notaMessage);
    }

    public List<Nota> updateNota(String alunoMatricula, Long disciplinaId, NotaMessage notaMessage) {
        List<Nota> notas = notaRepository.findByAlunoMatriculaAndDisciplinaId(alunoMatricula, disciplinaId);

        if (!notas.isEmpty()) {
            List<Nota> updatedNotas = new ArrayList<>();

            for (int i = 0; i < notas.size() && i < notaMessage.getValor().size(); i++) {
                Nota nota = notas.get(i);
                nota.setValor(notaMessage.getValor().get(i));
                updatedNotas.add(save(nota));
            }

            return updatedNotas;
        }

        return null;
    }


    public NotaMessage findNotaByAlunoAndDisciplina(String alunoMatricula, Long disciplinaId) {
        List<Nota> notas = notaRepository.findByAlunoMatriculaAndDisciplinaId(alunoMatricula, disciplinaId);
        if (notas == null || notas.isEmpty()) {
            return null;
        }

        List<Double> notaValues = notas.stream()
                .map(Nota::getValor)
                .collect(Collectors.toList());

        return new NotaMessage(alunoMatricula, disciplinaId, notaValues);
    }


}
