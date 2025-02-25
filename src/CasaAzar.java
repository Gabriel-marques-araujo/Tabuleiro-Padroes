
public class CasaAzar extends Casa{

    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.printf("| Jogador %s caiu na casa de Azar\n", jogador.getCor());

        if (!jogador.getTipoJogador().equals("Sorte")) {
            System.out.printf("| Jogador %s perdeu 3 moedas\n", jogador.getCor());
            jogador.setQtdMoedas(jogador.getQtdMoedas() - 3);
        } else {
            System.out.printf("| Não perdeu 3 moedas, pois Jogador %s é Sortudo\n", jogador.getCor());
        }
        if (jogador.getQtdMoedas() <= 0){
            jogador.setQtdMoedas(0);
            System.out.printf("| Jogador %s tem 0 moedas\n", jogador.getCor());
        } else {
            System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
        }
    }
}