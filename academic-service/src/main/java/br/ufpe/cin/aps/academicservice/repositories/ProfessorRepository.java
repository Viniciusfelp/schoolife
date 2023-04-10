package br.ufpe.cin.aps.academicservice.repositories;

import br.ufpe.cin.aps.academicservice.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
