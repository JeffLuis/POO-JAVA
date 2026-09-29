public enum TipoManutencao {
    PREVENTIVA,
    CORRETIVA;

    public static TipoManutencao parse(String tipoStr) {
        if (tipoStr == null) {
            throw new IllegalArgumentException("O tipo de manutenção não pode ser nulo.");
        }
        try {
            return TipoManutencao.valueOf(tipoStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de manutenção inválido: " + tipoStr);
        }
    }
}