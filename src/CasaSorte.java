public class CasaSorte extends Casa{

    public CasaSorte(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.printf("| Jogador %s caiu na casa especial de Avançar 3 casas\n", jogador.getCor());

        if (!jogador.getTipoJogador().equals("Azarado")) {
            jogador.setCasaAtual((jogador.getCasaAtual() + 3));
            System.out.printf("| Jogador %s  mudou para casa atual: %d\n", jogador.getCor(), jogador.getCasaAtual());
            System.out.printf("| Jogador %s ganhou 3 moedas\n", jogador.getCor());
            jogador.setQtdMoedas(jogador.getQtdMoedas() + 3);
        } else {
            System.out.printf("| Não mudou, pois Jogador %s é Azarado casa atual: %d\n", jogador.getCor(), jogador.getCasaAtual());
        }

        System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
    }
}