package com.aps.schoolife.fachada;

import com.aps.schoolife.controllers.*;
import com.aps.schoolife.models.*;
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
    private RegistroAcademicoController registroAcademicoController;

    @Autowired
    private ProfessorController professorController;

    @Autowired
    private TurmaController turmaController;

    public void cadastrarAluno(String nome, String cpf, String email, String senha, String telefone, String endereco) {
        alunoController.cadastrarAluno(new Aluno(cpf, nome, email, null));
    }

    public void cadastrarDisciplina(String codigo, String nome) {
        disciplinaController.cadastrarDisciplina(new Disciplina(codigo, nome,null, null));
    }

    public void cadastrarNota(Aluno aluno, Disciplina disciplina, Turma turma, double nota) {
        registroAcademicoController.cadastrarNota(new RegistroAcademico(null, aluno, disciplina, turma, nota, null));
    }

    public void cadastrarProfessor(String nome, String cpf, String email, String senha, String telefone, String endereco) {
        professorController.cadastrarProfessor(new Professor(cpf, nome, email, null));
    }

    public void cadastrarTurma(String codigo, String nome, Professor professor, String turno) {
        turmaController.cadastrarTurma(new Turma( 1,nome, turno, null));
    }

    public void atualizarAluno(String nome, String cpf, String email, String senha, String telefone, String endereco) {
        alunoController.atualizarAluno(cpf, new Aluno(cpf, nome, email, null));
    }

    public void atualizarDisciplina(String codigo, String nome) {
        disciplinaController.atualizarDisciplina(null, new Disciplina(codigo, nome,null, null));
    }

    public void atualizarNota(long idNota, Aluno aluno, Disciplina disciplina, Turma turma, double nota) {
        registroAcademicoController.atualizarNota(idNota, new RegistroAcademico(null, aluno, disciplina, turma, nota,null));
    }

    public void atualizarProfessor(String nome, String cpf, String email, String senha, String telefone, String endereco) {
        professorController.atualizarProfessor(cpf, new Professor(cpf, nome, email, null));
    }

    public void atualizarTurma(Long codigo, String nome, Professor professor, String turno) {
        turmaController.atualizarTurma(codigo, new Turma( 1,nome, turno, null));
    }

    public void removerAluno(String cpf) {
        alunoController.removerAluno(cpf);
    }

}
