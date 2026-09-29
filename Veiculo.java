import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Veiculo {
    
    private final String placa;
    private String modelo;
    private double quilometragemAtual;
    private double quilometragemUltimaPreventiva;
    private double intervaloManutencaoKm;
    private final List<Manutencao> historicoManutencoes;

    public Veiculo(String placa, String modelo, double quilometragemAtual, double intervaloManutencaoKm) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa não pode ser vazia.");
        }
        if (quilometragemAtual < 0) {
            throw new IllegalArgumentException("A quilometragem inicial não pode ser negativa.");
        }
        if (intervaloManutencaoKm <= 0) {
            throw new IllegalArgumentException("O intervalo de manutenção deve ser maior que zero.");
        }

        this.placa = placa;
        this.modelo = modelo;
        this.quilometragemAtual = quilometragemAtual;
        this.quilometragemUltimaPreventiva = quilometragemAtual;
        this.intervaloManutencaoKm = intervaloManutencaoKm;
        this.historicoManutencoes = new ArrayList<>();
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public double getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public double getQuilometragemUltimaPreventiva() {
        return quilometragemUltimaPreventiva;
    }

    public double getIntervaloManutencaoKm() {
        return intervaloManutencaoKm;
    }

    public List<Manutencao> getHistoricoManutencoes() {
        return Collections.unmodifiableList(historicoManutencoes);
    }
    
    public void atualizarQuilometragem(double novaQuilometragem) {
        if (novaQuilometragem < this.quilometragemAtual) {
            throw new IllegalArgumentException("A nova quilometragem (" + novaQuilometragem 
                + " km) não pode ser menor que a atual (" + this.quilometragemAtual + " km).");
        }
        this.quilometragemAtual = novaQuilometragem;
    }

    public void registrarManutencao(Manutencao manutencao) {
        if (manutencao == null) {
            throw new IllegalArgumentException("A manutenção não pode ser nula.");
        }
        
        if (manutencao.getTipo() == TipoManutencao.PREVENTIVA) {
            this.quilometragemUltimaPreventiva = manutencao.getQuilometragem();
        }

        this.historicoManutencoes.add(manutencao);
    }
    
    public boolean precisaManutencao() {
        double rodadosDesdeUltimaPreventiva = this.quilometragemAtual - this.quilometragemUltimaPreventiva;
        return rodadosDesdeUltimaPreventiva >= this.intervaloManutencaoKm;
    }
    
    public float custoTotalManutencao() {
        float total = 0f;
        for (Manutencao m : historicoManutencoes) {
            total += m.getCusto();
        }
        return total;
    }

    public void imprimirRelatorio() {
        System.out.println("Placa: " + placa);
        System.out.println("Modelo: " + modelo);
        System.out.println("KM atual: " + quilometragemAtual);
        System.out.println("Precisa manutencao? " + (precisaManutencao() ? "SIM" : "NAO"));
        System.out.println("Custo total de manutencao: R$ " + custoTotalManutencao());
        System.out.println("Historico:");
        for (Manutencao m : historicoManutencoes) {
            System.out.println("  " + m.getDataFormatada() + " - " + m.getTipo() + " - R$ " + m.getCusto());
        }
    }
}