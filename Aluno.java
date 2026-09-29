public record Aluno(String nome) {
    public Aluno {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
        }
        nome = nome.trim();
    }
}