package br.ufpe.cin.aps.notafrequenciaservice.models;

public class NotaDTO {

    private Long id;
    private AlunoDTO aluno;
    private DisciplinaDTO disciplina;
    private Double valor;

    public NotaDTO(Long id, AlunoDTO aluno, DisciplinaDTO disciplina, Double valor) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
