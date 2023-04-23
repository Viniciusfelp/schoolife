package br.ufpe.cin.aps.notafrequenciaservice.models;

import java.time.LocalDate;

public class FrequenciaDTO {

    private Long id;
    private AlunoDTO aluno;
    private DisciplinaDTO disciplina;
    private LocalDate data;
    private Boolean presenca;

    public FrequenciaDTO(Long id, AlunoDTO aluno, DisciplinaDTO disciplina, LocalDate data, Boolean presenca) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.data = data;
        this.presenca = presenca;
    }

    public FrequenciaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlunoDTO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoDTO aluno) {
        this.aluno = aluno;
    }

    public DisciplinaDTO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaDTO disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getPresenca() {
        return presenca;
    }

    public void setPresenca(Boolean presenca) {
        this.presenca = presenca;
    }
}
