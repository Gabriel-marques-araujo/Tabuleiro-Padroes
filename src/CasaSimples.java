public class CasaSimples extends Casa{

    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        System.out.printf("| Jogador %s caiu na Casa Simples\n", jogador.getCor());
        System.out.printf("| Jogador %s ganhou 1 moeda\n", jogador.getCor());
        jogador.setQtdMoedas(jogador.getQtdMoedas() + 1);
        System.out.printf("| Jogador %s tem %d moedas\n", jogador.getCor(), jogador.getQtdMoedas());
    }
}