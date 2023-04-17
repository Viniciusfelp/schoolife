package br.ufpe.cin.aps.academicservice.repositories;

import br.ufpe.cin.aps.academicservice.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    Optional<Object> findByEmail(String email);
}
