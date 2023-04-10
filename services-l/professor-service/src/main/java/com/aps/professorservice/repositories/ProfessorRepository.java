package com.aps.professorservice.repositories;

import com.aps.professorservice.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
