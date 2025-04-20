import java.util.Scanner;

public class AppNBAInsercao {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Jogador[] listaJogadores2 = new Jogador[3922];
        Jogador[] listaJogadores = new Jogador[3922];

        int off = 0;

        ArquivoTextoLeitura arqLeitura;
        String linha;

        arqLeitura = new ArquivoTextoLeitura("/tmp/jogadores.txt");
        linha = arqLeitura.ler();
        linha = arqLeitura.ler();

        while (linha != null) {

            if (off == 0) {
                String[] dados = linha.split(",");

                String estadoNascimento = "";
                String cidadeNascimento = "";
                String universidade = "a";
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int altura = Integer.parseInt(dados[2]);
                int peso = Integer.parseInt(dados[3]);
                int anoNascimento = Integer.parseInt(dados[5]);


                universidade = dados[4];
                if (universidade.isEmpty() || universidade.equals("")) {
                    universidade = "nao informado";
                }

                try {
                    cidadeNascimento = dados[6];
                } catch (ArrayIndexOutOfBoundsException excecao) {
                    cidadeNascimento = "nao informado";
                }

                try {
                    estadoNascimento = dados[7];
                } catch (ArrayIndexOutOfBoundsException excecao) {
                    estadoNascimento = "nao informado";
                }

                listaJogadores2[id] = new Jogador(id, altura, peso, anoNascimento, nome, universidade, cidadeNascimento, estadoNascimento);

            }


            linha = arqLeitura.ler();
        }
        int count_tam = 0;

        while (sc.hasNextLine()) {
            linha = sc.nextLine();
            if (linha.equals("FIM")) {
                break;
            }
            int id_jog = Integer.parseInt(linha);
            listaJogadores[count_tam] = listaJogadores2[id_jog];
            count_tam += 1;
        }

        boolean check;
        for (int i = 1; i < count_tam; i++) {
            Jogador tmp = listaJogadores[i];
            int j = i - 1;

            while ((j >= 0) && (listaJogadores[j].getAnoNascimento() > tmp.getAnoNascimento() || (listaJogadores[j].getAnoNascimento() == tmp.getAnoNascimento() && listaJogadores[j].getNome().compareTo(tmp.getNome()) > 0))) {
                check = false;
                if (listaJogadores[j].getAnoNascimento() > tmp.getAnoNascimento()) {
                    listaJogadores[j + 1] = listaJogadores[j];
                    check = true;
                } else if (listaJogadores[j].getAnoNascimento() == tmp.getAnoNascimento()) {
                    if (listaJogadores[j].getNome().compareTo(tmp.getNome()) > 0) {
                        listaJogadores[j + 1] = listaJogadores[j];
                        check = true;
                    }
                }

                if (check) {
                    j--;
                }

            }
            listaJogadores[j + 1] = tmp;
        }


        int z;
        for (z = 0; z < count_tam; z++) {
            listaJogadores[z].imprime();
        }


    }


}