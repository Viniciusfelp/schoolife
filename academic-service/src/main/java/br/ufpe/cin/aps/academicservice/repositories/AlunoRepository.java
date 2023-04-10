package br.ufpe.cin.aps.academicservice.repositories;

import br.ufpe.cin.aps.academicservice.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
