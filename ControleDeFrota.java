import java.util.HashMap;
import java.util.Map;

public class ControleDeFrota {

    private static final Map<String, Veiculo> veiculos = new HashMap<>();

    public static void cadastrarVeiculo(String placa, String modelo, double kmAtual, double intervaloManutencao) {
        if (veiculos.containsKey(placa)) {
            throw new IllegalArgumentException("Veículo com a placa " + placa + " já está cadastrado.");
        }
        Veiculo veiculo = new Veiculo(placa, modelo, kmAtual, intervaloManutencao);
        veiculos.put(placa, veiculo);
    }

    public static void atualizarQuilometragem(String placa, double novaQuilometragem) {
        Veiculo veiculo = buscarVeiculoOuAvisar(placa);
        if (veiculo != null) {
            veiculo.atualizarQuilometragem(novaQuilometragem);
        }
    }

    public static void registrarManutencao(String placa, String data, String tipoStr, float custo) {
        Veiculo veiculo = buscarVeiculoOuAvisar(placa);
        if (veiculo != null) {
            TipoManutencao tipo = TipoManutencao.parse(tipoStr);
            Manutencao manutencao = new Manutencao(data, tipo, custo, veiculo.getQuilometragemAtual());
            veiculo.registrarManutencao(manutencao);
        }
    }

    public static boolean precisaManutencao(String placa) {
        Veiculo veiculo = veiculos.get(placa);
        if (veiculo == null) {
            return false;
        }
        return veiculo.precisaManutencao();
    }

    public static float custoTotalManutencao(String placa) {
        Veiculo veiculo = veiculos.get(placa);
        if (veiculo == null) {
            return 0f;
        }
        return veiculo.custoTotalManutencao();
    }

    public static void imprimirRelatorio(String placa) {
        Veiculo veiculo = buscarVeiculoOuAvisar(placa);
        if (veiculo != null) {
            veiculo.imprimirRelatorio();
        }
    }

    private static Veiculo buscarVeiculoOuAvisar(String placa) {
        Veiculo veiculo = veiculos.get(placa);
        if (veiculo == null) {
            System.out.println("Veiculo nao encontrado: " + placa);
        }
        return veiculo;
    }
}