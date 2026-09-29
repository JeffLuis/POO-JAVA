import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Manutencao {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate data;
    private final TipoManutencao tipo;
    private final float custo;
    private final double quilometragem; 

    public Manutencao(String dataStr, TipoManutencao tipo, float custo, double quilometragem) {
        if (dataStr == null || dataStr.trim().isEmpty()) {
            throw new IllegalArgumentException("A data da manutenção é obrigatória.");
        }

        LocalDate dataParsed;
        try {
            dataParsed = LocalDate.parse(dataStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy.");
        }
        
        if (dataParsed.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data da manutenção não pode ser futura.");
        }
        
        if (custo < 0) {
            throw new IllegalArgumentException("O custo da manutenção não pode ser negativo.");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("O tipo de manutenção é obrigatório.");
        }

        this.data = dataParsed;
        this.tipo = tipo;
        this.custo = custo;
        this.quilometragem = quilometragem;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataFormatada() {
        return data.format(FORMATTER);
    }

    public TipoManutencao getTipo() {
        return tipo;
    }

    public float getCusto() {
        return custo;
    }

    public double getQuilometragem() {
        return quilometragem;
    }
}