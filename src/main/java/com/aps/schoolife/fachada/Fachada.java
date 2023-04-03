package com.aps.schoolife.fachada;

import com.aps.schoolife.models.*;
import com.aps.schoolife.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class Fachada {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private SalaService salaService;

    // Métodos para Professores
    public Professor cadastrarProfessor(Professor professor) {
        return professorService.cadastrarProfessor(professor);
    }

    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    public Professor buscarProfessorPorId(String id) {
        return professorService.buscarProfessorPorId(id);
    }

    public Professor atualizarProfessor(String id, Professor professor) {
        return professorService.atualizarProfessor(id, professor);
    }

    public void deletarProfessor(String id) {
        professorService.deletarProfessor(id);
    }

    // Métodos para Alunos
    public Aluno cadastrarAluno(Aluno aluno) {
        alunoService.cadastrarAluno(aluno);
        return aluno;
    }

    public List<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }

    public Optional<Aluno> buscarAlunoPorId(String id) {
        return alunoService.buscarAlunoPorId(id);
    }

    public Aluno atualizarAluno(String id, Aluno aluno) {
        alunoService.atualizarAluno(id, aluno);
        return aluno;
    }

    public void deletarAluno(String id) {
        alunoService.deletarAluno(id);
    }

    // Métodos para Turmas
    public Turma cadastrarTurma(Turma turma) {
        return turmaService.cadastrarTurma(turma);
    }

    public List<Turma> listarTurmas() {
        return turmaService.listarTurmas();
    }

    public Optional<Turma> buscarTurmaPorId(Long id) {
        return turmaService.buscarTurmaPorId(id);
    }

    public Turma atualizarTurma(Long id, Turma turma) {
        return turmaService.atualizarTurma(id, turma);
    }

    public void deletarTurma(Long id) {
        turmaService.deletarTurma(id);
    }

    // Métodos para Disciplinas
    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaService.cadastrarDisciplina(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.listarDisciplinas();
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaService.buscarDisciplinaPorId(id);
    }

    public Disciplina atualizarDisciplina(Long id, Disciplina disciplina) {
        return disciplinaService.atualizarDisciplina(id, disciplina);
    }

    public void deletarDisciplina(Long id) {
        disciplinaService.deletarDisciplina(id);
    }

    // Métodos para Salas
    public void cadastrarSala(Sala sala) {
        salaService.cadastrarSala(sala);
    }

    public List<Sala> listarSalas() {
        return salaService.listarSalas();
    }

    public Sala buscarSalaPorId(Long id) {
        return salaService.buscarSalaPorId(id);
    }

    public void atualizarSala(Sala sala) {
        salaService.atualizarSala(sala);
    }

    public void deletarSala(Long id) {
        salaService.deletarSalas(id);
    }

    public Aluno inscreverAlunoEmAtividade(String alunoId, Long atividadeId) {
        return alunoService.inscreverAlunoEmAtividade(alunoId, atividadeId);
    }

    public Aluno removerAlunoDaAtividade(String alunoId, Long atividadeId) {
        return alunoService.removerAlunoDaAtividade(alunoId, atividadeId);
    }
}