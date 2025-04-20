import java.util.Scanner;

class Celula {

    private Jogador item;
    private Celula proximo;

    public Celula(Jogador novo) {

        item = novo;
        proximo = null;
    }

    public Celula() {

        item = new Jogador();
        proximo = null;
    }

    public Jogador getItem() {
        return item;
    }

    public void setItem(Jogador item) {
        this.item = item;
    }

    public Celula getProximo() {
        return proximo;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}

class Fila {

    private Celula frente;
    private Celula tras;

    public Fila() {

        Celula sentinela;

        sentinela = new Celula();
        frente = sentinela;
        tras = sentinela;
    }

    public boolean filaVazia() {

        boolean resp;

        if (frente == tras)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void enfileirar(Jogador novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);

        tras.setProximo(novaCelula);
        tras = novaCelula;
    }

    public Jogador desenfileirar() throws Exception {

        Celula celulaDesenfileirada;
        Celula proximaCelula;

        if (!filaVazia()) {

            celulaDesenfileirada = frente.getProximo();
            proximaCelula = celulaDesenfileirada.getProximo();
            frente.setProximo(proximaCelula);

            if (celulaDesenfileirada == tras) // se a célula retirada for a última célula da fila
                tras = frente;

            return (celulaDesenfileirada.getItem());
        } else
            throw new Exception("Não foi possível desenfileirar nenhum item: a fila está vazia!");
    }

    public int obterMediaAltura() throws Exception {
        Celula aux;
        double alturas = 0;
        double media_altura;
        int count = 0;

        if (!filaVazia()) {

            aux = frente.getProximo();

            while (aux != null) {
                alturas += aux.getItem().getAltura();
                aux = aux.getProximo();
                count += 1;
            }

            media_altura = alturas / count;

            return (int) media_altura;

        } else
            throw new Exception("Não foi possível imprimir o conteúdo da fila: a fila está vazia!");

    }

    public void mostrar() throws Exception {

        Celula aux;
        int posicao = 0;

        if (!filaVazia()) {

            aux = frente.getProximo();

            while (aux != null) {
                Jogador jogador_da_vez = aux.getItem();
                System.out.println("[" + posicao + "] ## " + jogador_da_vez.getId() + " ## " + jogador_da_vez.getNome() + " ## " + jogador_da_vez.getAltura() + " ## " + jogador_da_vez.getPeso() + " ## " + jogador_da_vez.getAnoNascimento() + " ## " + jogador_da_vez.getUniversidade() + " ## " + jogador_da_vez.getCidadeNascimento() + " ## " + jogador_da_vez.getEstadoNascimento() + "##");
                aux = aux.getProximo();
                posicao += 1;
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da fila: a fila está vazia!");
    }
}

public class AppNBAFilaCelula {
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


        Fila fila_jogadores;
        fila_jogadores = new Fila();
        int altura_media_jogs;

        while (sc.hasNextLine()) {

            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off2 = 1;
            }

            if (off2 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    fila_jogadores.enfileirar(listaJogadores[id_lido]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }

                try {
                    altura_media_jogs = fila_jogadores.obterMediaAltura();
                    System.out.println(altura_media_jogs);
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
                                fila_jogadores.enfileirar(listaJogadores[id_insert]);
                            } catch (Exception e) {
//                                    System.out.println(e.getMessage());
                            }

                            try {
                                altura_media_jogs = fila_jogadores.obterMediaAltura();
                                System.out.println(altura_media_jogs);
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else {

                            try {
                                desenfileirado_jog = fila_jogadores.desenfileirar();
                                System.out.println("(R) " + desenfileirado_jog.getNome());

                            } catch (Exception e) {
//                                    System.out.println(e.getMessage());
                            }

                        }

                    }

                    try {
                        fila_jogadores.mostrar();
                    } catch (Exception e) {
//                    System.out.println(e.getMessage());
                    }

                    break;
                }
            }


        }


    }

}