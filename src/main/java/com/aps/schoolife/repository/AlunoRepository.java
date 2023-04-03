package com.aps.schoolife.repository;

import com.aps.schoolife.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Aluno findByEmail(String email);
}
