import java.util.ArrayList;
import java.util.Random;

public class CasaSurpresa extends Casa{

    public CasaSurpresa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        ArrayList<Jogador> jogadores = tabuleiro.getJogadores();
        int index = -1;
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).equals(jogador)) {
                index = i;
            }
        }

        System.out.printf("| Jogador %s caiu na casa especial de Troca de Tipo\n", jogador.getCor());
        Random gerador = new Random();
        int carta = gerador.nextInt(1,4);


        System.out.printf("| Jogador %s puxou a carta Número %d\n", jogador.getCor(), carta);

        if (carta == 1) {
            Jogador trocado1 = new JogadorNormal(jogador.getCor(), jogador.getCasaAtual());
            trocado1.setQtdJogadas(jogador.getQtdJogadas());
            jogadores.set(index, trocado1);
        }

        if (carta == 2) {
            Jogador trocado2 = new JogadorSortudo(jogador.getCor(), jogador.getCasaAtual());
            trocado2.setQtdJogadas(jogador.getQtdJogadas());
            jogadores.set(index, trocado2);
        }

        if (carta == 3 ) {
            Jogador trocado3 = new JogadorAzarado(jogador.getCor(), jogador.getCasaAtual());
            trocado3.setQtdJogadas(jogador.getQtdJogadas());
            jogadores.set(index, trocado3);
        }

        System.out.printf("| Jogador %s mudou para %s\n", jogadores.get(index).getCor(), jogadores.get(index).getTipoJogador());
    }
}