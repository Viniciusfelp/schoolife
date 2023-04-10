package com.aps.turmaservice.repositories;

import com.aps.turmaservice.models.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
