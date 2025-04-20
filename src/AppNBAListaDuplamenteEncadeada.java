import java.util.Scanner;

class Celula {

    private Jogador item;
    private Celula anterior;
    private Celula proximo;

    public Celula(Jogador novo) {

        item = novo;
        anterior = null;
        proximo = null;
    }

    public Celula() {

        item = new Jogador();
        anterior = null;
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

    public Celula getAnterior() {
        return anterior;
    }

    public void setAnterior(Celula anterior) {
        this.anterior = anterior;
    }
}

class ListaDuplamenteEncadeada {

    private Celula primeiro;
    private Celula ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {

        Celula sentinela;

        sentinela = new Celula();

        primeiro = sentinela;
        ultimo = sentinela;

        tamanho = 0;
    }

    public boolean listaVazia() {

        boolean resp;

        if (primeiro == ultimo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void inserir(Jogador novo, int posicao) throws Exception {

        Celula anterior, novaCelula, proximaCelula;

        if ((posicao >= 0) && (posicao <= tamanho)) {
            anterior = primeiro;
            for (int i = 0; i < posicao; i++)
                anterior = anterior.getProximo();

            novaCelula = new Celula(novo);

            proximaCelula = anterior.getProximo();

            anterior.setProximo(novaCelula);
            novaCelula.setAnterior(anterior);
            novaCelula.setProximo(proximaCelula);
            proximaCelula.setAnterior(novaCelula);

            if (posicao == tamanho)  // a inserção ocorreu na última posição da lista
                ultimo = novaCelula;

            tamanho++;

        } else
            throw new Exception("Não foi possível inserir o item na lista: a posição informada é inválida!");
    }

    public void inserirInicio(Jogador novo) {

        Celula novaCelula, proximaCelula;

        novaCelula = new Celula(novo);

        proximaCelula = primeiro.getProximo();

        primeiro.setProximo(novaCelula);
        proximaCelula.setAnterior(novaCelula);

        novaCelula.setAnterior(primeiro);
        novaCelula.setProximo(proximaCelula);

        if (novaCelula.getProximo() == null) {
            ultimo = novaCelula;
        }

        tamanho++;

    }

    public void inserirFinal(Jogador novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);

        ultimo.setProximo(novaCelula);
        novaCelula.setAnterior(ultimo);

        ultimo = novaCelula;

        tamanho++;

    }

    public Jogador remover(int posicao) throws Exception {

        Celula anterior, celulaRemovida, proximaCelula;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {
                anterior = primeiro;
                for (int i = 0; i < posicao; i++)
                    anterior = anterior.getProximo();

                celulaRemovida = anterior.getProximo();

                proximaCelula = celulaRemovida.getProximo();

                anterior.setProximo(proximaCelula);
                proximaCelula.setAnterior(anterior);
                celulaRemovida.setProximo(null);
                celulaRemovida.setAnterior(null);

                if (celulaRemovida == ultimo)
                    ultimo = anterior;

                tamanho--;

                return (celulaRemovida.getItem());
            } else
                throw new Exception("Não foi possível remover o item da lista: a posição informada é inválida!");
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public Jogador removerInicio() throws Exception {

        Celula anterior, celulaRemovida, proximaCelula;

        if (!listaVazia()) {

            anterior = primeiro;

            celulaRemovida = anterior.getProximo();

            proximaCelula = celulaRemovida.getProximo();

            anterior.setProximo(proximaCelula);
            proximaCelula.setAnterior(anterior);
            celulaRemovida.setProximo(null);
            celulaRemovida.setAnterior(null);

            if (celulaRemovida == ultimo)
                ultimo = anterior;

            tamanho--;

            return (celulaRemovida.getItem());
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public Jogador removerFinal() throws Exception {

        Celula removida, penultima;

        if (!listaVazia()) {

            removida = ultimo;

            penultima = ultimo.getAnterior();
            penultima.setProximo(null);
            removida.setAnterior(null);

            ultimo = penultima;

            tamanho--;

            return (removida.getItem());
        } else
            throw new Exception("Não foi possível remover o último item da lista: a lista está vazia!");
    }

    public void mostrar() throws Exception {

        Celula aux;

        if (!listaVazia()) {
            aux = primeiro.getProximo();
            int count = 0;
            while (aux != null) {
                Jogador jog = aux.getItem();
                System.out.println("[" + count + "] ## " + jog.getId() + " ## " + jog.getNome() + " ## " + jog.getAltura() + " ## " + jog.getPeso() + " ## " + jog.getAnoNascimento() + " ## " + jog.getUniversidade() + " ## " + jog.getCidadeNascimento() + " ## " + jog.getEstadoNascimento() + " ##");
                aux = aux.getProximo();
                count += 1;
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
    }
}

public class AppNBAListaDuplamenteEncadeada {
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

        Jogador removido_lista;
        ListaDuplamenteEncadeada Listaencadeada_jogadores;
        Listaencadeada_jogadores = new ListaDuplamenteEncadeada();

        while (sc.hasNextLine()) {

            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off2 = 1;
            }

            if (off2 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    Listaencadeada_jogadores.inserirFinal(listaJogadores[id_lido]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }


            } else {

                if (!linha_lida_console.equals("FIM") && !linha_lida_console.equals("")) {
                    int repeticao = Integer.parseInt(linha_lida_console);

                    for (int j = 0; j < repeticao; j++) {
                        String linha2 = sc.nextLine();

                        String[] dados_linha_2 = linha2.split(" ");

                        String value = dados_linha_2[0];

                        if (value.equals("II")) {
                            try {
                                Listaencadeada_jogadores.inserirInicio(listaJogadores[Integer.parseInt(dados_linha_2[1])]);
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (value.equals("IF")) {
                            try {
                                Listaencadeada_jogadores.inserirFinal(listaJogadores[Integer.parseInt(dados_linha_2[1])]);
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (value.equals("I*")) {
                            try {
                                Listaencadeada_jogadores.inserir(listaJogadores[Integer.parseInt(dados_linha_2[2])], Integer.parseInt(dados_linha_2[1]));
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (value.equals("RI")) {
                            try {
                                removido_lista = Listaencadeada_jogadores.removerInicio();
                                System.out.println("(R) " + removido_lista.getNome());

                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (value.equals("RF")) {
                            try {
                                removido_lista = Listaencadeada_jogadores.removerFinal();
                                System.out.println("(R) " + removido_lista.getNome());

                            } catch (Exception e) {
//                              System.out.println(e.getMessage());
                            }
                        } else if (value.equals("R*")) {

                            try {
                                removido_lista = Listaencadeada_jogadores.remover(Integer.parseInt(dados_linha_2[1]));
                                System.out.println("(R) " + removido_lista.getNome());

                            } catch (Exception e) {
//                              System.out.println(e.getMessage());
                            }

                        }
                    }

                    try {
                        Listaencadeada_jogadores.mostrar();
                    } catch (Exception e) {
//                    System.out.println(e.getMessage());
                    }


                }
            }


        }


    }

}