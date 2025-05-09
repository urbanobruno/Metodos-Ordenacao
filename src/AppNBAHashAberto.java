import java.util.Scanner;

class TabelaHash {

    private int M;
    private Jogador tabelaHash[];

    public TabelaHash(int tamanho) {

        this.M = tamanho;

        tabelaHash = new Jogador[this.M];
        for (int i = 0; i < this.M; i++)
            tabelaHash[i] = null;
    }

    private int funcaoHash(int chave, int tentativa) {

        return (((chave % this.M) + (tentativa * (chave % 23)))) % this.M;

    }

    public void inserir(Jogador novo) throws Exception {

        int posicao, tentativa;
        boolean inseriu = false;

        tentativa = 0;

        while ((!inseriu) && (tentativa < this.M)) {
            posicao = funcaoHash(novo.getAltura(), tentativa);

            if (tabelaHash[posicao] == null) {
                tabelaHash[posicao] = novo;
                inseriu = true;
            } else if (tabelaHash[posicao].getId() == novo.getId())
                throw new Exception("Erro ao tentar inserir o novo elemento na tabela hash: o elemento já foi inserido anteriormente!");
            else
                // colisão
                tentativa++;
        }
    }

    public int pesquisar(Jogador chave) {

        int posicao, tentativa;

        tentativa = 0;

        while (tentativa < this.M) {
            posicao = funcaoHash(chave.getAltura(), tentativa);

            if (tabelaHash[posicao] == null)
                return -1;
            else if (tabelaHash[posicao].getId() == chave.getId()) {
                return posicao;
            } else {
                // colisão
                tentativa++;
            }
        }

        return -1;
    }

    public void imprimir() {

        System.out.println("Conteúdo da tabela hash:");
        for (int i = 0; i < this.M; i++) {
            System.out.print("Posição: " + i + " --> ");
            if (tabelaHash[i] != null)
                tabelaHash[i].imprime();
            else
                System.out.println("posição vazia");
        }
    }
}

public class AppNBAHashAberto {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Jogador[] listaJogadores = new Jogador[3922];
        int off = 0;
        int off2 = 0;

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

                listaJogadores[id] = new Jogador(id, altura, peso, anoNascimento, nome, universidade, cidadeNascimento, estadoNascimento);

            }


            linha = arqLeitura.ler();
        }

        TabelaHash hash = new TabelaHash(79);
        int off5 = 0;

        while (sc.hasNextLine()) {

            // antes FIM
            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off5 = 1;
            }

            if (off5 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    hash.inserir(listaJogadores[id_lido]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }
            } else {
                // Depois FIM
                if (!linha_lida_console.equals("FIM") && !linha_lida_console.equals("")) {

                    while (sc.hasNextLine()) {

                        if (linha_lida_console.equals("FIM")) {
                            break;
                        }

                        boolean xyz = false;
                        int g;
                        Jogador temp = null;
                        for (g = 0; g < listaJogadores.length; g++) {
                            if (listaJogadores[g] != null) {
                                if (listaJogadores[g].getNome().equals(linha_lida_console)) {
                                    temp = listaJogadores[g];
                                    int achou = hash.pesquisar(temp);
                                    if (achou == -1) {
                                        System.out.println("NAO");
                                    } else {
                                        System.out.println(achou + " SIM");
                                    }
                                    xyz = true;
                                }
                            }
                        }

                        if (xyz == false) {
                            System.out.println("NAO");
                        }

                        linha_lida_console = sc.nextLine();

                    }

                    break;

                }
            }


        }


    }

}