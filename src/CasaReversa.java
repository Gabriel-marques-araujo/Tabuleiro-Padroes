import java.util.ArrayList;

public class CasaReversa extends Casa{

    public CasaReversa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        ArrayList<Jogador> jogadores = tabuleiro.getJogadores();

        System.out.printf("| Jogador %s caiu na casa especial Trocar com Menor Casa\n", jogador.getCor());
        int menorCasa = tabuleiro.getNumCasas();
        int indMenor = 0;

        for (int j = 0; j < jogadores.size(); j++) {
            if (jogadores.get(j).getCasaAtual() < menorCasa) {
                menorCasa = jogadores.get(j).getCasaAtual();
                indMenor = j;
            }
        }

        int posicaoAtual = jogador.getCasaAtual();
        jogador.setCasaAtual(menorCasa);
        jogadores.get(indMenor).setCasaAtual(posicaoAtual);

        System.out.printf("| Jogador %s tem agora casa atual: %d\n", jogador.getCor(), jogador.getCasaAtual());
        if (jogador.getCasaAtual() != menorCasa) {
            System.out.printf("| jogador %s tem agora casa atual: %d\n", jogadores.get(indMenor).getCor(), jogadores.get(indMenor).getCasaAtual());
        }
    }
}