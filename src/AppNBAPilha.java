import java.util.Scanner;

class Pilha {

    private Jogador pilha[];
    private int topo;

    public Pilha(int tamanho) {
        pilha = new Jogador[tamanho];
        topo = 0;
    }

    public Pilha() {
        pilha = new Jogador[0];
        topo = 0;
    }

    public boolean pilhaCheia() {

        boolean resp;

        if (topo == pilha.length)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public boolean pilhaVazia() {

        boolean resp;

        if (topo == 0)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public Jogador desempilhar() throws Exception {

        Jogador desempilhado;

        if (!pilhaVazia()) {
            topo--;
            desempilhado = pilha[topo];
            return desempilhado;
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public void empilhar(Jogador novo) throws Exception {

        if (!pilhaCheia()) {
            pilha[topo] = novo;
            topo++;
        } else
            throw new Exception("Não foi possível empilhar: a pilha está cheia!");
    }

    public Jogador consultarTopo() throws Exception {

        if (!pilhaVazia()) {
            return (pilha[topo - 1]);
        } else
            throw new Exception("Não foi possível consultar o elemento do topo da pilha: a pilha está vazia!");
    }

    public void mostrar() throws Exception {
        int l;
        if (!pilhaVazia()) {
            for (l = 0; l < topo; l++) {
                System.out.println("[" + l + "] ## " + pilha[l].getId() + " ## " + pilha[l].getNome() + " ## " + pilha[l].getAltura() + " ## " + pilha[l].getPeso() + " ## " + pilha[l].getAnoNascimento() + " ## " + pilha[l].getUniversidade() + " ## " + pilha[l].getCidadeNascimento() + " ## " + pilha[l].getEstadoNascimento() + " ##");

            }

        } else
            throw new Exception("Não foi possível mostrar a pilha: a pilha está vazia!");
    }


}

public class AppNBAPilha {
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
        Jogador desenfileirado_jog;

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


        Pilha pilha_jogadores;
        pilha_jogadores = new Pilha(3922);

        while (sc.hasNextLine()) {

            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off2 = 1;
            }

            if (off2 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    pilha_jogadores.empilhar(listaJogadores[id_lido]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }


            } else {

                if (!linha_lida_console.equals("FIM") && !linha_lida_console.equals("")) {
                    int repeticao = Integer.parseInt(linha_lida_console);

                    for (int j = 0; j < repeticao; j++) {
                        String linha2 = sc.nextLine();

                        String[] dados_linha_2 = linha2.split(" ");

                        if (dados_linha_2.length == 2) {
                            int id_insert = Integer.parseInt(dados_linha_2[1]);
                            try {
                                pilha_jogadores.empilhar(listaJogadores[id_insert]);
                            } catch (Exception e) {
//                                    System.out.println(e.getMessage());
                            }

                        } else {

                            try {
                                desenfileirado_jog = pilha_jogadores.desempilhar();
                                System.out.println("(R) " + desenfileirado_jog.getNome());

                            } catch (Exception e) {
//                                    System.out.println(e.getMessage());
                            }

                        }

                    }

                    try {
                        pilha_jogadores.mostrar();
                    } catch (Exception e) {
//                    System.out.println(e.getMessage());
                    }


                }
            }


        }


    }

}