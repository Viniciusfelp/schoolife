package com.aps.schoolife.repository;

import com.aps.schoolife.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoCpf(String cpf);

    List<Nota> findByDisciplinaId(Long id);

    List<Nota> findByAlunoCpfAndDisciplinaId(String cpf, Long id);
}
