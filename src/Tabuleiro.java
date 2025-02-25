import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
    private final ArrayList<Jogador> jogadores = new ArrayList<>();
    private final ArrayList<Casa> casas = new ArrayList<>();
    private int numCasas;

    private static Tabuleiro instancia;

    protected Tabuleiro() {

    }

    public static synchronized Tabuleiro getInstancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    public void criarJogadores(int numJogadores) {
        Random gerador = new Random();
        Scanner teclado = new Scanner(System.in);


        do  {
            int tipoJogador1 = (gerador.nextInt(3) + 1);
            String cor;
            boolean corUsada;
            String corDiferente;
            boolean entradaValida;

            do {
                corUsada = false;
                entradaValida = true;

                System.out.print("Escolha a cor do jogador: ");
                cor = teclado.nextLine();

                if (cor.isEmpty()){
                    System.out.println("Não pode ficar vazio");
                    corUsada = true;
                    continue;
                }

                for (char c : cor.toCharArray()) {
                    if (!Character.isLetter(c)) {
                        entradaValida = false;
                        break;
                    }
                }
                if (!entradaValida) {
                    System.out.println("Entrada Invalida");
                    continue;
                }

                for (Jogador jogador : jogadores) {
                    if (jogador.getCor().equalsIgnoreCase(cor)) {
                        corUsada = true;
                        break;
                    }
                }

            } while (corUsada || !entradaValida);

            if (tipoJogador1 == 1) {
                Jogador jogador = JogadorFactory.criarJogador(cor, "normal");
                jogadores.add(jogador);
            }

            if (tipoJogador1 == 2) {
                Jogador jogador = JogadorFactory.criarJogador(cor, "sortudo");
                jogadores.add(jogador);
            }

            if (tipoJogador1 == 3) {
                Jogador jogador = JogadorFactory.criarJogador(cor, "azarado");
                jogadores.add(jogador);
            }

            while (jogadores.size() < 2) {
                int tipoJogador2 = 0;
                corUsada = false;

                if (tipoJogador1 == 1) {
                    tipoJogador2 = gerador.nextInt(2, 4);
                }

                if (tipoJogador1 == 2) {
                    tipoJogador2 = gerador.nextInt(3, 5);
                }

                if (tipoJogador1 == 3) {
                    tipoJogador2 = gerador.nextInt(3, 5);
                    if (tipoJogador2 == 3) {
                        tipoJogador2--;
                    }
                }

                do {
                    entradaValida = true;

                    System.out.print("Escolha uma cor diferente para o outro jogador: ");
                    corDiferente = teclado.nextLine();

                    if (corDiferente.isEmpty()) {
                        System.out.println("Não pode ficar vazio");
                        corUsada = true;
                        continue;
                    }

                    for (char j : corDiferente.toCharArray()) {
                        if (!Character.isLetter(j)) {
                            entradaValida = false;
                            break;
                        }
                    }
                    if (!entradaValida) {
                        System.out.println("Entrada Invalida");
                        continue;
                    }

                    for (Jogador jogador : jogadores) {
                        if (jogador.getCor().equalsIgnoreCase(corDiferente)) {
                            corUsada = true;
                            break;
                        }
                    }

                } while (!entradaValida) ;


                if (!corUsada) {
                    if (tipoJogador2 == 2) {
                        Jogador jogadorDiferente = JogadorFactory.criarJogador(corDiferente, "sortudo");
                        jogadores.add(jogadorDiferente);
                    }

                    if (tipoJogador2 == 3) {
                        Jogador jogadorDiferente = JogadorFactory.criarJogador(corDiferente, "azarado");
                        jogadores.add(jogadorDiferente);
                    }

                    if (tipoJogador2 == 4) {
                        Jogador jogadorDiferente = JogadorFactory.criarJogador(corDiferente, "normal");
                        jogadores.add(jogadorDiferente);
                    }
                }
            }
        } while ((this.jogadores.size() < numJogadores));

        System.out.print("+-------------------------------------+\n");
        System.out.println("Jogadores Cadastrados");
        for (Jogador jogador : jogadores) {
            System.out.println("Jogador: " + jogador.getCor() + " " + jogador.getTipoJogador());
        }
    }

    public void criarCasas(int numCasas) {
        this.numCasas = numCasas;

        Scanner teclado = new Scanner(System.in);
        if (numCasas > 0) {
            int numCasasDisponiveis = numCasas;

            int numCasasSimples = numCasasDisponiveis;
            for (int i = 0; i < numCasasSimples; i++) {
                Casa casa = CasaFactory.criarCasa("simples", i);
                casas.add(casa);
            }

            System.out.print("+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas de Troca: ");
            int numCasasTroca = teclado.nextInt();
            for (int i = 0; i < numCasasTroca; i++) {
                System.out.print("Digite um número para a casa de Troca: ");
                int numero = teclado.nextInt();
                Casa casa = CasaFactory.criarCasa("troca", numero);
                casas.set(numero, casa);
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasTroca;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas de JogaDeNovo: ");
            int numCasasJogaDeNovo = teclado.nextInt();
            if (numCasasJogaDeNovo <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasJogaDeNovo; i++) {
                    System.out.print("Digite o número da casa de JogaDeNovo: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("jogaDeNovo", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasJogaDeNovo;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas Reversa: ");
            int numCasasReversa = teclado.nextInt();
            if (numCasasReversa <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasReversa; i++) {
                    System.out.print("Digite o número da casa de Reversa: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("reversa", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasReversa;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas Azar: ");
            int numCasasAzar = teclado.nextInt();
            if (numCasasAzar <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasAzar; i++) {
                    System.out.print("Digite o número da casa de Azar: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("azar", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasAzar;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas Sorte: ");
            int numCasasSorte = teclado.nextInt();
            if (numCasasSorte <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasSorte; i++) {
                    System.out.print("Digite o número da casa de Sorte: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("sorte", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasSorte;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas Prisão: ");
            int numCasasPrisao = teclado.nextInt();
            if (numCasasPrisao <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasPrisao; i++) {
                    System.out.print("Digite o número da casa Prisão: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("prisão", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");
            numCasasDisponiveis -= numCasasPrisao;

            System.out.print("\n+-------------------------------------+\n");
            System.out.print("Digite a quantidade de casas Surpresa: ");
            int numCasasSurpresa = teclado.nextInt();
            if (numCasasSurpresa <= numCasasDisponiveis) {
                for (int i = 0; i < numCasasSurpresa; i++) {
                    System.out.print("Digite o número da casa Surpresa: ");
                    int numero = teclado.nextInt();
                    Casa casa = CasaFactory.criarCasa("surpresa", numero);
                    casas.set(numero, casa);
                }
            } else {
                System.out.println("Não existem casas disponíveis!");
            }
            System.out.print("+-------------------------------------+\n");

        } else {
            System.out.println("Nenhuma casa foi criada!");
            System.out.print("+-------------------------------------+\n");
        }
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public int getNumCasas() {
        return numCasas;
    }
}
