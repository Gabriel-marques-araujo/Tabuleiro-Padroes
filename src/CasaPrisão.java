import java.util.Scanner;

public class CasaPrisão extends Casa {
    private static final Scanner scanner = new Scanner(System.in);

    public CasaPrisão(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if (!jogador.getBloqueado()) {
            System.out.printf("| Jogador %s caiu na casa especial de Prisão!\n", jogador.getCor());
            jogador.setBloqueado(true);
            jogador.setRodadasPreso(2);
            System.out.println("| Você está preso e não jogará nas próximas 2 rodadas!");
        } else if (jogador.getRodadasPreso() == 2) {
            System.out.printf("| Jogador %s está preso! Deseja pagar 2 moedas para sair? (y/n): ", jogador.getCor());
            String resposta = scanner.nextLine().toLowerCase();
            if (resposta.equalsIgnoreCase("y")) {
                if (jogador.getQtdMoedas() >= 2) {
                    jogador.removerMoedas(2);
                    jogador.setBloqueado(false);
                    jogador.setRodadasPreso(0);
                    System.out.printf("| %s pagou 2 moedas e saiu da prisão!\n", jogador.getCor());
                    System.out.printf("| %s tem agora %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
                } else {
                    System.out.printf("| %s não tem moedas suficientes para pagar a taxa e permanecerá preso por mais 1 rodada!\n", jogador.getCor());
                    jogador.setRodadasPreso(1);
                }
            } else {
                jogador.setRodadasPreso(1);
                System.out.printf("| %s não pagou a taxa e permanecerá preso por mais 1 rodada!\n", jogador.getCor());
            }
        } else {
            jogador.setBloqueado(false);
            jogador.setRodadasPreso(0);
            System.out.printf("| %s cumpriu as 2 rodadas e está livre!\n", jogador.getCor());
        }
    }
}
