import java.util.Scanner;

public class CasaTroca extends Casa{

    public CasaTroca(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.printf("| Jogador %s caiu na casa de Troca\n", jogador.getCor());
        boolean continuar = false;

        Scanner teclado = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("--------------------------");
            System.out.println("| Escolha uma das opções: ");
            System.out.println("| 1 - Boné (3 moedas)" + " Beneficio: Avançar 1 casa e Ganhar 3 moedas\n");
            System.out.println("| 2 - Moleton (4 moedas)" + " Beneficio: Avançar 2 casas e Ganhar 4 moedas\n");
            System.out.println("| 3 - Óculos Escuros (5 moedas) " + " Beneficio: Avançar 3 casas e Ganhar 5 moedas\n");
            System.out.print("|Digite a opção desejada: ");
            opcao = teclado.nextLine();

            if (jogador.getQtdMoedas() < 3 && opcao.equals("1")) {
                System.out.println("Moedas insuficientes para comprar o Boné!");
                System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
                return;
            } else if (jogador.getQtdMoedas() < 4 && opcao.equals("2")) {
                System.out.println("Moedas insuficientes para comprar o Moleton!");
                System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
                return;
            } else if (jogador.getQtdMoedas() < 5 && opcao.equals("3")) {
                System.out.println("Moedas insuficientes para comprar os Óculos Escuros!");
                System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
                return;
            }

            if (jogador.getAcessorio().equals("Boné") && opcao.equals("1")) {
                System.out.printf("| O jogador %s já possui o Boné\n", jogador.getCor());
                continuar = true;
            } else if (jogador.getAcessorio().equals("Moleton") && opcao.equals("2")) {
                System.out.printf("| O Jogador %s já possui o Moleton\n", jogador.getCor());
                continuar = true;
            } else if (jogador.getAcessorio().equals("Óculos Escuros") && opcao.equals("3")) {
                System.out.printf("| O Jogador %s já possui os Óculos Escuros\n", jogador.getCor());
                continuar = true;
            } else if (jogador.getAcessorio().equals("Moleton") && opcao.equals("1")) {
                System.out.printf("| O Jogador %s não pode comprar o Boné, já possui o Moleton\n", jogador.getCor());
                continuar = true;
            }
        } while(continuar);

        if (jogador.getAcessorio().equals("Óculos Escuros")){
            System.out.printf("| Jogador %s não comprou, possui o maior prêmio\n", jogador.getCor());
            System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
            return;
        }

        switch (opcao) {
            case "1": {
                System.out.printf("| Jogador %s escolheu o Boné!\n", jogador.getCor());
                jogador.setQtdMoedas(jogador.getQtdMoedas() - 3);
                jogador.setAcessorio("Boné");
                break;
            }
            case "2": {
                System.out.printf("| Jogador %s escolheu o Moleton!\n", jogador.getCor());
                jogador.setQtdMoedas(jogador.getQtdMoedas() - 4);
                jogador.setAcessorio("Moleton");
                break;
            }
            case "3": {
                System.out.printf("| Jogador %s escolheu os Óculos Escuros!\n", jogador.getCor());
                jogador.setQtdMoedas(jogador.getQtdMoedas() - 5);
                jogador.setAcessorio("Óculos Escuros");
                break;
            }
            default: {
                System.out.println("Entrada Inválida!");
                break;
            }
        }

        System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
    }
}