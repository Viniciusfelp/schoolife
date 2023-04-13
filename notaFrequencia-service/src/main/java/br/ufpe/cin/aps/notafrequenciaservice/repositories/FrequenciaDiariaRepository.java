package br.ufpe.cin.aps.notafrequenciaservice.repositories;

import br.ufpe.cin.aps.notafrequenciaservice.models.FrequenciaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequenciaDiariaRepository extends JpaRepository<FrequenciaDiaria, Long> {
}
