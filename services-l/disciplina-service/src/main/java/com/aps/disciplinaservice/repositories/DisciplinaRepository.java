package com.aps.disciplinaservice.repositories;

import com.aps.disciplinaservice.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
