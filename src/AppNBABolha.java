import java.io.*;
import java.util.Scanner;

class ArquivoTextoEscrita {
    private BufferedWriter saida;

    ArquivoTextoEscrita(String nomeArquivo) {
        try {
            saida = new BufferedWriter(new OutputStreamWriter(new
                    FileOutputStream(nomeArquivo), "UTF-8"));
        } catch (UnsupportedEncodingException excecao) {
            System.out.println(excecao.getMessage());
        } catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de escrita: " +
                    excecao);
        }
    }

    public void fecharArquivo() {
        try {
            saida.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de escrita: " +
                    excecao);
        }
    }

    public void escrever(String textoEntrada) {
        try {
            saida.write(textoEntrada);
            saida.newLine();
        } catch (IOException excecao) {
            System.out.println("Erro de entrada/saída " + excecao);
        }
    }
}

public class AppNBABolha {
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

        for (int i = (count_tam - 1); i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (listaJogadores[j].getCidadeNascimento().compareTo(listaJogadores[j + 1].getCidadeNascimento()) > 0) {

                    Jogador temp = listaJogadores[j];
                    listaJogadores[j] = listaJogadores[j + 1];
                    listaJogadores[j + 1] = temp;
                } else if (listaJogadores[j].getCidadeNascimento().compareTo(listaJogadores[j + 1].getCidadeNascimento()) == 0) {
                    if (listaJogadores[j].getNome().compareTo(listaJogadores[j + 1].getNome()) > 0) {
                        Jogador temp = listaJogadores[j];
                        listaJogadores[j] = listaJogadores[j + 1];
                        listaJogadores[j + 1] = temp;
                    }
                }
            }
        }

        int z;
        for (z = 0; z < count_tam; z++) {
            listaJogadores[z].imprime();
        }


    }


}