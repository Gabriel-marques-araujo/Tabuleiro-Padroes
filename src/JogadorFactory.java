public class JogadorFactory {

    public static Jogador criarJogador(String cor, String tipo){
        return switch (tipo.toLowerCase()) {
            case "sortudo" -> new JogadorSortudo(cor,0);
            case "azarado" -> new JogadorAzarado(cor,0);
            case "normal" -> new JogadorNormal(cor,0);
            default -> null;
        };
    }
}
