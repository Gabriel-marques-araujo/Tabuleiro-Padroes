import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private boolean existeVencedor = false;
    private Jogador vencedor;

    public void configTabuleiro(int numCasas){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.criarCasas(numCasas);
    }

    public void config(int numJogadores){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        tabuleiro.criarJogadores(numJogadores);
    }

    public void printTabuleiro(Jogador jogador){
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        ArrayList<Casa> casas = tabuleiro.getCasas();

        do {
            System.out.print("\n+-------------------------------------+\n");
            if(jogador.getResultadosIguais()) {
                jogador.setResultadosIguais(false);
            }
            jogador.jogar();
            if (jogador.getCasaAtual() < casas.size()){
                for (Casa casa : casas) {
                    if (jogador.getCasaAtual() == casa.getNumero()) {
                        casa.aplicarRegra(jogador);
                        if (!(jogador.getResultadosIguais())) {
                            break;
                        }
                    }
                }
            } else {
                vencedor = jogador;
                existeVencedor = true;
                jogador.setCasaAtual(casas.size());
                break;
            }
            System.out.print("+-------------------------------------+\n\n");
        } while (jogador.getResultadosIguais());
    }

    public void start() {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        ArrayList<Jogador> jogadores = tabuleiro.getJogadores();
        Scanner teclado = new Scanner(System.in);

        while (!this.existeVencedor) {
            System.out.print("+-------------------------------------+\n");
            System.out.println("| Iniciando Nova Rodada\n");

            System.out.print("| Deseja ativar o Modo Debug(y/n): ");
            String escolha = teclado.nextLine();
            switch (escolha) {
                case "y": {
                    ativarModoDebug();
                    for (Jogador jogador : jogadores) {
                        printTabuleiro(jogador);
                        if (existeVencedor) {
                            System.out.print("+-------------------------------------+\n");
                            break;
                        }
                    }
                    break;
                }
                case "n": {
                    for (Jogador jogador : jogadores) {
                        printTabuleiro(jogador);
                        if (existeVencedor) {
                            System.out.print("+-------------------------------------+\n");
                            break;
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Entrada Inválida\n");
                    break;
                }
            }

            System.out.println("\nRodada encerrada!");
            System.out.println("+-------------------------------------+\n");
        }

        System.out.println("+--------------PLACAR FINAL------------+");
        System.out.printf("| Jogador Vencedor: %s\n", vencedor.getCor());

        for (Jogador jogador : jogadores) {
            System.out.printf("| Jogador %s teve %d jogadas\n", jogador.getCor(), jogador.getQtdJogadas());
            System.out.printf("| Jogador %s casa final: %d\n", jogador.getCor(), jogador.getCasaAtual());
            System.out.printf("| Jogador %s terminou com %d moedas\n", jogador.getCor(), jogador.getQtdJogadas());
        }

        System.out.println("+--------------------------------+");

    }

    public void ativarModoDebug() {
        Tabuleiro tabuleiro = Tabuleiro.getInstancia();
        ArrayList<Jogador> jogadores = tabuleiro.getJogadores();
        ArrayList<Casa> casas = tabuleiro.getCasas();
        Scanner teclado = new Scanner(System.in);
        int casaEscolhida = 0;
        boolean jogadorFoiEscolhido = false;

        System.out.print("+-------------------------------------+\n");
        System.out.println("Casas Especiais no Tabuleiro:");


        for (Casa casa : casas) {
            if (!(casa instanceof CasaSimples)) {
                System.out.println("Casa " + casa.getNumero() + " - " + casa.getClass().getSimpleName());
            }
        }
        System.out.print("+-------------------------------------+\n");

        do {

            System.out.print("+-------------------------------------+\n");
            System.out.print("Digite a cor do Jogador Escolhido: ");
            String corEscolhida = teclado.nextLine();

            for (Jogador jogador : jogadores) {
                if (jogador.getCor().equals(corEscolhida)) {
                    System.out.print("Digite a nova posição do jogador: ");

                    boolean entradaValida = false;

                    while (!entradaValida) {
                        if (teclado.hasNextInt()) {
                            casaEscolhida = teclado.nextInt();
                            teclado.nextLine();
                            entradaValida = true;
                        } else {
                            System.out.println(" Entrada inválida!");
                            System.out.print("Digite a nova posição do jogador: ");
                            teclado.next();

                        }

                    }
                    System.out.print("+-------------------------------------+\n");

                    jogador.setCasaAtual(casaEscolhida);
                    jogadorFoiEscolhido = true;
                    jogador.setDebugger(true);
                    break;
                }
            }

            if (!jogadorFoiEscolhido) {
                System.out.println("Jogador não encontrado ou entrada inválida! Por favor, tente novamente.!");
                System.out.println("+-------------------------------------+\n");
            }

        } while (!jogadorFoiEscolhido);

        System.out.println("Jogador Escolhido com Sucesso!");
        System.out.println("+-------------------------------------+\n");
    }
}