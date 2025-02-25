public class CasaFactory {

    public static Casa criarCasa(String tipo, int numero){
        return switch (tipo.toLowerCase()) {
            case "simples" -> new CasaSimples(numero);
            case "surpresa" -> new CasaSurpresa(numero);
            case "prisão" -> new CasaPrisão(numero);
            case "sorte" -> new CasaSorte(numero);
            case "azar" -> new CasaAzar(numero);
            case "reversa" -> new CasaReversa(numero);
            case "jogadenovo" -> new CasaJogaDeNovo(numero);
            case "troca" -> new CasaTroca(numero);
            default -> null;
        };
    }
}
