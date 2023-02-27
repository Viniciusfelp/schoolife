package com.aps.schoolife.fachada;

import com.aps.schoolife.controllers.*;
import com.aps.schoolife.models.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class Fachada {

    @Autowired
    private AlunoController alunoController;

    @Autowired
    private DisciplinaController disciplinaController;

    @Autowired
    private NotaController notaController;

    @Autowired
    private ProfessorController professorController;

    @Autowired
    private TurmaController turmaController;


}
