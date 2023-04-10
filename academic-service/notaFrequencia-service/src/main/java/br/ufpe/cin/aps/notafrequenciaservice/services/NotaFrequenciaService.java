package br.ufpe.cin.aps.notafrequenciaservice.services;

import br.ufpe.cin.aps.notafrequenciaservice.models.*;
import br.ufpe.cin.aps.servicoregistronotafrequencia.models.FrequenciaDiaria;
import br.ufpe.cin.aps.notafrequenciaservice.repositories.NotaFrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFrequenciaService {

    @Autowired
    private NotaFrequenciaRepository notaFrequenciaRepository;

    public NotaFrequencia save(NotaFrequencia notaFrequencia) {
        return notaFrequenciaRepository.save(notaFrequencia);
    }

    public NotaFrequencia update(Long id, NotaFrequencia updatedNotaFrequencia) {
        Optional<NotaFrequencia> optionalNotaFrequencia = notaFrequenciaRepository.findById(id);

        if (optionalNotaFrequencia.isPresent()) {
            NotaFrequencia notaFrequencia = optionalNotaFrequencia.get();
            notaFrequencia.setAlunoMatricula(updatedNotaFrequencia.getAlunoMatricula());
            notaFrequencia.setDisciplinaId(updatedNotaFrequencia.getDisciplinaId());
            notaFrequencia.setNota(updatedNotaFrequencia.getNota());
            notaFrequencia.setFrequenciasDiarias(updatedNotaFrequencia.getFrequenciasDiarias());
            return notaFrequenciaRepository.save(notaFrequencia);
        } else {
            throw new RuntimeException("NotaFrequencia not found");
        }
    }

    public void delete(Long id) {
        notaFrequenciaRepository.deleteById(id);
    }

    public NotaFrequencia findById(Long id) {
        return notaFrequenciaRepository.findById(id).orElseThrow(() -> new RuntimeException("NotaFrequencia not found"));
    }

    public List<NotaFrequencia> findAll() {
        return notaFrequenciaRepository.findAll();
    }
}