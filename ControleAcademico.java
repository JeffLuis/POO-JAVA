import java.util.HashMap;
import java.util.Map;

public class ControleAcademico {

    private static final Map<String, Turma> turmas = new HashMap<>();

    public static void criarTurma(String codigo, int capacidade) {
        if (turmas.containsKey(codigo)) {
            throw new IllegalArgumentException("Turma com o código " + codigo + " já existe.");
        }
        turmas.put(codigo, new Turma(codigo, capacidade));
    }

    public static void matricular(String codigoTurma, String nomeAluno) {
        Turma turma = turmas.get(codigoTurma);
        if (turma == null) {
            System.out.println("Turma nao encontrada: " + codigoTurma);
            return;
        }
        turma.matricular(new Aluno(nomeAluno));
    }

    public static void lancarNota(String codigoTurma, String nomeAluno, double valorNota) {
        Turma turma = turmas.get(codigoTurma);
        if (turma == null) {
            System.out.println("Turma nao encontrada: " + codigoTurma);
            return;
        }
        turma.lancarNota(new Aluno(nomeAluno), new Nota(valorNota));
    }

    public static int quantidadeDeAprovados(String codigoTurma) {
        Turma turma = turmas.get(codigoTurma);
        if (turma == null) {
            return 0;
        }
        return turma.quantidadeDeAprovados();
    }

    public static void imprimirTurma(String codigoTurma) {
        Turma turma = turmas.get(codigoTurma);
        if (turma == null) {
            System.out.println("Turma nao encontrada: " + codigoTurma);
            return;
        }
        turma.imprimirTurma();
    }
}