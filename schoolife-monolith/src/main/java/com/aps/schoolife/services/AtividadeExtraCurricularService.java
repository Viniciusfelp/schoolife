package com.aps.schoolife.services;

import com.aps.schoolife.models.AtividadeExtraCurricular;
import com.aps.schoolife.repository.AtividadeExtraCurricularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeExtraCurricularService {

    @Autowired
    private AtividadeExtraCurricularRepository repository;

    public AtividadeExtraCurricular create(AtividadeExtraCurricular atividadeExtraCurricular) {
        return repository.save(atividadeExtraCurricular);
    }

    public Optional<AtividadeExtraCurricular> findById(Long id) {
        return repository.findById(id);
    }

    public List<AtividadeExtraCurricular> findAll() {
        return repository.findAll();
    }

    public AtividadeExtraCurricular update(AtividadeExtraCurricular atividadeExtraCurricular) {
        return repository.save(atividadeExtraCurricular);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
