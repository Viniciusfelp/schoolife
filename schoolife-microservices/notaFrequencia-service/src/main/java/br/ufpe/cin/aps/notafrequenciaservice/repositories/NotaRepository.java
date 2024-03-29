package br.ufpe.cin.aps.notafrequenciaservice.repositories;

import br.ufpe.cin.aps.notafrequenciaservice.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoMatriculaAndDisciplinaId(String matricula, Long idDisciplina);

    List<Nota> findByDisciplinaId(Long idDisciplina);

    List<Nota> findByAlunoMatricula(String matricula);
}
