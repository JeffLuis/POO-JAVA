import java.util.Objects;
import java.util.Optional;

public class Matricula {
    private final Aluno aluno;
    private Nota nota;

    public Matricula(Aluno aluno) {
        this.aluno = Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        this.nota = null; 
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Optional<Nota> getNota() {
        return Optional.ofNullable(nota);
    }

    public void setNota(Nota nota) {
        this.nota = Objects.requireNonNull(nota, "A nota não pode ser nula.");
    }

    public boolean isAprovado() {
        return nota != null && nota.isAprovado();
    }

    public String getSituacaoNota() {
        return nota == null ? "sem nota" : nota.toString();
    }
}