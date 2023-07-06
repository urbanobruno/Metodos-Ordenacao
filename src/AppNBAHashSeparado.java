import java.io.*;
import java.util.Scanner;

class Celula {

    private Jogador item;
    private Celula proximo;

    Celula(Jogador item) {
        this.item = item;
        this.proximo = null;
    }

    Celula() {
        this.item = new Jogador();
        this.proximo = null;
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

class ListaEncadeada {

    private Celula primeiro;
    private Celula ultimo;
    private int tamanho;

    ListaEncadeada() {

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

    public void inserir(Jogador item, int posicao) throws Exception {

        Celula aux;
        Celula nova;
        Celula proxima;
        int i;

        if ((posicao >= 0) && (posicao <= tamanho)) {
            for (i = 0, aux = primeiro; i < posicao; i++)
                aux = aux.getProximo();

            nova = new Celula(item);

            proxima = aux.getProximo();
            aux.setProximo(nova);
            nova.setProximo(proxima);

            if (posicao == tamanho)
                ultimo = nova;

            tamanho++;
        } else
            throw new Exception("Não foi possível inserir o item na lista: posição inválida!");
    }

    public Jogador retirar(int posicao) throws Exception {

        Celula aux;
        Celula retirada;
        Celula proxima;
        Jogador item = null;

        int i;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {
                for (i = 0, aux = primeiro; i < posicao; i++)
                    aux = aux.getProximo();

                retirada = aux.getProximo();

                proxima = retirada.getProximo();

                aux.setProximo(proxima);

                if (ultimo == retirada)
                    ultimo = aux;

                tamanho--;

                item = retirada.getItem();

            } else
                throw new Exception("Não foi possível retirar o item da lista: posição inválida!");
        } else
            throw new Exception("Não foi possível retirar o item da lista: a lista está vazia!");

        return item;
    }

    public Jogador pesquisar(String dado) {

        Celula aux;

        aux = primeiro.getProximo();

        while (aux != null) {
            if (aux.getItem().getNome().equals(dado)) {
                return aux.getItem();
            }
            aux = aux.getProximo();
        }
        return null;
    }

    public void imprimir() throws Exception {

        Celula aux;

        if (!listaVazia()) {
            aux = primeiro.getProximo();
            while (aux != null) {
                aux.getItem().imprime();

                aux = aux.getProximo();
            }
        } else
            throw new Exception("A lista está vazia!");
    }
}

class TabelaHash {

    private int M;
    private ListaEncadeada tabelaHash[];

    public TabelaHash(int tamanho) {

        this.M = tamanho;

        tabelaHash = new ListaEncadeada[this.M];
        for (int i = 0; i < M; i++)
            tabelaHash[i] = new ListaEncadeada();
    }

    private int funcaoHash(int chave) {

        return (chave % this.M);
    }

    public void inserir(Jogador novo) throws Exception {

        int posicao;

        posicao = funcaoHash(novo.getAltura());

        if (tabelaHash[posicao].pesquisar(novo.getNome()) == null)
            tabelaHash[posicao].inserir(novo, 0);
        else
            throw new Exception("Não foi possível inserir o novo elemento na tabela hash: o elemento já havia sido inserido anteriormente!");
    }

    public int pesquisar(String chave) {

        int i = 0;

        while (i <= 37 || tabelaHash[i].pesquisar(chave) == null) {
            if (tabelaHash[i].pesquisar(chave) != null) {
                Jogador temp = tabelaHash[i].pesquisar(chave);
                return funcaoHash(temp.getAltura());
            }
            if (i == 36) {
                break;
            }
            i++;
        }

        return -1;
    }

    public void imprimir() {

        System.out.println("Conteúdo da tabela hash:");
        for (int i = 0; i < M; i++) {
            System.out.println("Posição: " + i);
            try {
                tabelaHash[i].imprimir();
            } catch (Exception erro) {
                System.out.println(erro.getMessage());
            }
        }
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

public class AppNBAHashSeparado {
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

        TabelaHash hash = new TabelaHash(37);
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

                        int achou = hash.pesquisar(linha_lida_console);
                        if (achou == -1) {
                            System.out.print("NAO");
                        } else {
                            System.out.print(achou + " SIM");
                        }


                        linha_lida_console = sc.nextLine();

                    }

                    break;

                }
            }


        }


    }

}