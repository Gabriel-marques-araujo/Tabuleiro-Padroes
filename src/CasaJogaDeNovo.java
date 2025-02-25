public class CasaJogaDeNovo extends Casa {

    public CasaJogaDeNovo(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {

        System.out.printf("| Jogador %s caiu na casa de Jogar de Novo\n", jogador.getCor());

        System.out.printf("| Dado 01: %d, Dado 02: %d\n", jogador.getDado01(), jogador.getDado02());

        System.out.printf("| Jogador %s ganhou %d moedas\n", jogador.getCor(), jogador.getSomaDados());

        jogador.setQtdMoedas(jogador.getQtdMoedas() + jogador.getSomaDados());

        System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());

        jogador.setResultadosIguais(true);
    }
}
