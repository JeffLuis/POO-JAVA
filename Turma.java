import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Turma {
    private final String codigo;
    private final int capacidade;
    private final List<Matricula> matriculas;

    public Turma(String codigo, int capacidade) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código da turma é obrigatório.");
        }
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade da turma deve ser maior que zero.");
        }
        this.codigo = codigo.trim();
        this.capacidade = capacidade;
        this.matriculas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getQuantidadeMatriculas() {
        return matriculas.size();
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    // Regra 4 & Regra 5: Valida aluno duplicado e limite de vagas
    public boolean matricular(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");

        for (Matricula m : matriculas) {
            if (m.getAluno().equals(aluno)) {
                System.out.println("Aluno ja matriculado nesta turma.");
                return false;
            }
        }

        if (matriculas.size() >= capacidade) {
            System.out.println("Turma cheia: " + codigo);
            return false;
        }

        matriculas.add(new Matricula(aluno));
        return true;
    }

    public boolean lancarNota(Aluno aluno, Nota nota) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo.");
        Objects.requireNonNull(nota, "Nota não pode ser nula.");

        for (Matricula m : matriculas) {
            if (m.getAluno().equals(aluno)) {
                m.setNota(nota);
                return true;
            }
        }
        System.out.println("Matricula nao encontrada.");
        return false;
    }
    
    public int quantidadeDeAprovados() {
        int aprovados = 0;
        for (Matricula m : matriculas) {
            if (m.isAprovado()) {
                aprovados++;
            }
        }
        return aprovados;
    }

    public void imprimirTurma() {
        System.out.println("Turma: " + codigo);
        for (Matricula m : matriculas) {
            System.out.println("  " + m.getAluno().nome() + " - nota: " + m.getSituacaoNota());
        }
        System.out.println("Aprovados: " + quantidadeDeAprovados());
    }
}