package br.ufpe.cin.aps.notafrequenciaservice.repositories;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
    List<Frequencia> findByAlunoMatricula(String matricula);

    List<Frequencia> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);
}
