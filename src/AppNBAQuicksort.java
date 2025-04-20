import java.util.Scanner;

public class AppNBAQuicksort {

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

        AppNBAQuicksort zs = new AppNBAQuicksort();

        zs.sort(listaJogadores, count_tam);

        int z;
        for (z = 0; z < count_tam; z++) {
            listaJogadores[z].imprime();
        }


    }

    public void sort(Jogador[] array, int n) {
        quicksort(array, 0, n - 1);
    }

    private void quicksort(Jogador[] array, int esq, int dir) {

        int part;
        if (esq < dir) {
            part = particao(array, esq, dir);
            quicksort(array, esq, part - 1);
            quicksort(array, part + 1, dir);
        }
    }

    private int particao(Jogador[] array, int inicio, int fim) {
        Jogador pivot = array[fim];
        int part = inicio - 1;
        for (int i = inicio; i < fim; i++) {
            if (array[i].getEstadoNascimento().compareTo(pivot.getEstadoNascimento()) < 0 || (array[i].getEstadoNascimento().compareTo(pivot.getEstadoNascimento()) == 0 && array[i].getNome().compareTo(pivot.getNome()) < 0)) {
                part++;
                swap(array, part, i);
            }
        }
        part++;
        swap(array, part, fim);
        return (part);
    }

    private void swap(Jogador[] array, int i, int j) {

        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}