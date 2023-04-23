package br.ufpe.cin.aps.notafrequenciaservice.repositories;

import br.ufpe.cin.aps.notafrequenciaservice.models.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    List<Frequencia> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);

    List<Frequencia> findByDisciplinaId(Long idDisciplina);

    List<Frequencia> findByAlunoMatriculaAndDisciplinaIdAndData(String matricula, Long idDisciplina, LocalDate data);

    @Query("SELECT COUNT(f) FROM Frequencia f WHERE f.alunoMatricula = :matricula AND f.disciplinaId = :idDisciplina AND f.presenca = :presenca")
    Long countByAlunoMatriculaAndDisciplinaIdAndPresenca(@Param("matricula") String matricula, @Param("idDisciplina") Long idDisciplina, @Param("presenca") Boolean presenca);
}
