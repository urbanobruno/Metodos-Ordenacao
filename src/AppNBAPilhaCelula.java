import java.io.*;
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

class Pilha {

    private Celula fundo;
    private Celula topo;

    public Pilha() {

        Celula sentinela;

        sentinela = new Celula();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean pilhaVazia() {

        boolean resp;

        if (topo == fundo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void empilhar(Jogador novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);
        novaCelula.setProximo(topo);
        topo = novaCelula;
    }

    public Jogador desempilhar() throws Exception {

        Celula desempilhado;

        if (!pilhaVazia()) {
            desempilhado = topo;
            topo = topo.getProximo();
            desempilhado.setProximo(null);
            return (desempilhado.getItem());
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public Jogador consultarTopo() throws Exception {

        if (!pilhaVazia()) {
            return (topo.getItem());
        } else
            throw new Exception("Não foi possível consultar o topo da pilha: a pilha está vazia!");
    }

    public void mostrar() throws Exception {

        Celula aux;
        Jogador[] lista_jogadores = new Jogador[3922];
        int count_lista = 0;
        int g;

        if (!pilhaVazia()) {

            aux = topo;
            while (aux.getProximo() != null) {
                lista_jogadores[count_lista] = aux.getItem();
                aux = aux.getProximo();
                count_lista += 1;
            }

            count_lista -= 1;

            for (g = 0; g < count_lista + 1; g++) {
                Jogador jogador_da_vez = lista_jogadores[count_lista - g];
                System.out.println("[" + g + "] ## " + jogador_da_vez.getId() + " ## " + jogador_da_vez.getNome() + " ## " + jogador_da_vez.getAltura() + " ## " + jogador_da_vez.getPeso() + " ## " + jogador_da_vez.getAnoNascimento() + " ## " + jogador_da_vez.getUniversidade() + " ## " + jogador_da_vez.getCidadeNascimento() + " ## " + jogador_da_vez.getEstadoNascimento() + " ##");
            }


        } else
            throw new Exception("Não foi possível imprimir o conteúdo da fila: a fila está vazia!");
    }
}


class Jogador {

    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    public Jogador() {
//        this.id = ;
        this.altura = 0;
        this.peso = 0;
        this.anoNascimento = 0;
        this.nome = "";
        this.universidade = "";
        this.cidadeNascimento = "";
        this.estadoNascimento = "";
    }

    public Jogador(int id, int altura, int peso, int anoNascimento, String nome, String universidade, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.nome = nome;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void imprime() {
        System.out.println("[" + this.getId() + " ## " + this.getNome() + " ## " + this.getAltura() + " ## " + this.getPeso() + " ## " + this.getAnoNascimento() + " ## " + this.getUniversidade() + " ## " + this.getCidadeNascimento() + " ## " + this.getEstadoNascimento() + "]");
    }

    public Jogador clone() {
        return new Jogador(id, altura, peso, anoNascimento, nome, universidade, cidadeNascimento, estadoNascimento);
    }

    public void ler(Jogador jogador) {
        System.out.println("ID:" + jogador.id);
        System.out.println("Altura:" + jogador.altura);
        System.out.println("Peso:" + jogador.peso);
        System.out.println("Ano Nascimento:" + jogador.anoNascimento);
        System.out.println("Nome:" + jogador.nome);
        System.out.println("Universidade:" + jogador.universidade);
        System.out.println("Cidade Nascimento:" + jogador.cidadeNascimento);
        System.out.println("Estado Nascimento" + jogador.estadoNascimento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

}


class ArquivoTextoLeitura {
    private BufferedReader entrada;

    ArquivoTextoLeitura(String nomeArquivo) {
        try {
            entrada = new BufferedReader(new InputStreamReader(new
                    FileInputStream(nomeArquivo), "UTF-8"));
        } catch (UnsupportedEncodingException excecao) {
            System.out.println(excecao.getMessage());
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    public void fecharArquivo() {
        try {
            entrada.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de leitura: " +
                    excecao);
        }
    }

    @SuppressWarnings("finally")
    public String ler() {
        String textoEntrada = null;
        try {
            textoEntrada = entrada.readLine();
        } catch (EOFException excecao) { //Excecao de final de arquivo.
            textoEntrada = null;
        } catch (IOException excecao) {
            System.out.println("Erro de leitura: " + excecao);
            textoEntrada = null;
        } finally {
            return textoEntrada;
        }
    }
}

public class AppNBAPilhaCelula {
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
        pilha_jogadores = new Pilha();

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