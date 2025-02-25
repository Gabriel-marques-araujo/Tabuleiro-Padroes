public class JogadorAzarado extends Jogador {

    public JogadorAzarado(String cor, int casaAtual) {
        super(cor, casaAtual);
        super.tipoJogador = "Azarado";
    }

    @Override
    public void jogar() {
        if (!(this.bloqueado || this.debugger )) {
            this.qtdJogadas++;
            do {
                this.setDado01((int) (Math.random() * 6 + 1));
                this.setDado02((int) (Math.random() * 6 + 1));
            } while (this.getDado01() + this.getDado02() > 6);

            this.somaDados = this.getDado01() + this.getDado02();
            System.out.printf("| Jogador %s %s jogou\n", this.getCor(),this.getTipoJogador());
            System.out.printf("| Dado 01: %d, Dado 02: %d\n", this.getDado01(), this.getDado02());

            if (this.getDado01() == this.getDado02()) {
                resultadosIguais = true;
            }

            switch (this.acessorio) {
                case "Boné": {
                    System.out.printf("| Jogador %s possui um Boné\n", this.getCor());
                    this.casaAtual = this.casaAtual + (this.somaDados + 1);
                    this.qtdMoedas += 3;
                    break;
                }
                case "Moleton": {
                    System.out.printf("| Jogador %s possui um Moleton\n", this.getCor());
                    this.casaAtual = this.casaAtual + (this.somaDados + 2);
                    this.qtdMoedas += 4;
                    break;
                }
                case "Óculos Escuros": {
                    System.out.printf("| Jogador %s possui um Óculos Escuros\n", this.getCor());
                    this.casaAtual = this.casaAtual + (this.somaDados + 3);
                    this.qtdMoedas += 5;
                    break;
                }
                default: {
                    this.casaAtual += this.somaDados;
                    break;
                }
            }

            System.out.printf("| Jogador %s casa atual: %d\n", this.getCor(), this.casaAtual);
        } else if (this.debugger) {
            System.out.printf("| Jogador %s não jogou, foi modificado no Debug!\n", this.getCor());
            this.debugger = false;
        }else {
            System.out.printf("| Jogador %s bloqueado\n", this.getCor());
        }

    }
}
