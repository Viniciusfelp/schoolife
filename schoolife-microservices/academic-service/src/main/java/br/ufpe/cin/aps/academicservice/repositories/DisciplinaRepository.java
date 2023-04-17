package br.ufpe.cin.aps.academicservice.repositories;

import br.ufpe.cin.aps.academicservice.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
