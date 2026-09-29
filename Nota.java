public class Nota {
    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 10.0;
    private static final double NOTA_APROVACAO = 6.0;

    private final double valor;

    public Nota(double valor) {
        if (valor < NOTA_MINIMA || valor > NOTA_MAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre " + NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public boolean isAprovado() {
        return this.valor >= NOTA_APROVACAO;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}