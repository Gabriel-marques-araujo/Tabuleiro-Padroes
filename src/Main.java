import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Bem Vindo!");
        System.out.print("Digite a quantidade de jogadores: ");
        int numJogadores = teclado.nextInt();
        System.out.print("Digite a quantidade de casas: ");
        int numCasas = teclado.nextInt();
        Jogo jogo = new Jogo();
        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.start();
    }
}