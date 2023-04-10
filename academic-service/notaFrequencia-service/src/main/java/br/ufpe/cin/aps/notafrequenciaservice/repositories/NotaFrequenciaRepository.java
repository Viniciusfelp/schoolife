package br.ufpe.cin.aps.notafrequenciaservice.repositories;

import br.ufpe.cin.aps.notafrequenciaservice.models.NotaFrequencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaFrequenciaRepository extends JpaRepository<NotaFrequencia, Long> {
}
