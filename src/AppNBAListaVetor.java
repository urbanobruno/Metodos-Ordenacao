import java.io.*;
import java.util.Scanner;


class ListaLinear {

    private Jogador lista[];
    private int primeiro;
    private int ultimo;
    private int tamanho;

    public ListaLinear(int M) {

        lista = new Jogador[M];
        tamanho = 0;
        primeiro = 0;
        ultimo = 0;
    }

    public ListaLinear() {
        lista = new Jogador[1];
        tamanho = 0;
        primeiro = 0;
        ultimo = 0;
    }

    public boolean listaVazia() {

        boolean resp;

        if (primeiro == ultimo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public boolean listaCheia() {

        boolean resp;

        if (ultimo == lista.length)
            // if (tamanho == lista.length)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void inserir(Jogador novo, int posicao) throws Exception {

        if (!listaCheia()) {
            if ((posicao >= 0) && (posicao <= tamanho)) {
                for (int i = ultimo; i > posicao; i--)
                    lista[i] = lista[i - 1];

                lista[posicao] = novo;

                ultimo++;
                tamanho++;
            } else
                throw new Exception("Não foi possível inserir o item na lista: posição informada é inválida!");
        } else
            throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
    }

    public void inserirInicio(Jogador novo) throws Exception {

        if (!listaCheia()) {
            for (int i = ultimo; i > primeiro; i--)
                lista[i] = lista[i - 1];

            lista[primeiro] = novo;

            ultimo++;
            tamanho++;
        } else
            throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
    }

    public void inserirFim(Jogador novo) throws Exception {

        if (!listaCheia()) {
            lista[ultimo] = novo;

            ultimo++;
            tamanho++;
        } else
            throw new Exception("Não foi possível inserir o item na lista: a lista está cheia!");
    }


    public Jogador remover(int posicao) throws Exception {

        Jogador removido;

        if (!listaVazia()) {
            if ((posicao >= 0) && (posicao < tamanho)) {

                removido = lista[posicao];
                tamanho--;

                for (int i = posicao; i < tamanho; i++) {
                    lista[i] = lista[i + 1];
                }

                ultimo--;

                return removido;
            } else
                throw new Exception("Não foi possível remover o item da lista: posição informada é inválida!");
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public Jogador removerFim() throws Exception {

        Jogador removido;

        if (!listaVazia()) {

            removido = lista[ultimo - 1];
            tamanho--;
            ultimo--;

            return removido;
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public Jogador removerInicio() throws Exception {

        Jogador removido;

        if (!listaVazia()) {

            removido = lista[primeiro];
            tamanho--;

            for (int i = primeiro; i < tamanho; i++) {
                lista[i] = lista[i + 1];
            }

            ultimo--;

            return removido;
        } else
            throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
    }

    public void mostrar() throws Exception {

        if (!listaVazia()) {

            for (int i = primeiro; i < ultimo; i++) {
                System.out.println("[" + i + "] ## " + lista[i].getId() + " ## " + lista[i].getNome() + " ## " + lista[i].getAltura() + " ## " + lista[i].getPeso() + " ## " + lista[i].getAnoNascimento() + " ## " + lista[i].getUniversidade() + " ## " + lista[i].getCidadeNascimento() + " ## " + lista[i].getEstadoNascimento() + " ##");
            }
        } else
            throw new Exception("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
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

public class AppNBAListaVetor {
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

        Jogador removido_lista;
        ListaLinear listalinear_jogadores;
        listalinear_jogadores = new ListaLinear(3922);

        while (sc.hasNextLine()) {

            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off2 = 1;
            }

            if (off2 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    listalinear_jogadores.inserirFim(listaJogadores[id_lido]);
                } catch (Exception e) {
//                    System.out.println(e.getMessage());
                }


            } else {

                if (!linha_lida_console.equals("FIM") && !linha_lida_console.equals("")) {
                    int repeticao = Integer.parseInt(linha_lida_console);

                    for (int j = 0; j < repeticao; j++) {
                        String linha2 = sc.nextLine();

                        String[] dados_linha_2 = linha2.split(" ");

                        if (dados_linha_2[0].equals("II")) {
                            try {
                                listalinear_jogadores.inserirInicio(listaJogadores[Integer.parseInt(dados_linha_2[1])]);
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (dados_linha_2[0].equals("IF")) {
                            try {
                                listalinear_jogadores.inserirFim(listaJogadores[Integer.parseInt(dados_linha_2[1])]);
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (dados_linha_2[0].equals("I*")) {
                            try {
                                listalinear_jogadores.inserir(listaJogadores[Integer.parseInt(dados_linha_2[2])], Integer.parseInt(dados_linha_2[1]));
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (dados_linha_2[0].equals("RI")) {
                            try {
                                removido_lista = listalinear_jogadores.removerInicio();
                                System.out.println("(R) " + removido_lista.getNome());
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }
                        } else if (dados_linha_2[0].equals("RF")) {

                            try {
                                removido_lista = listalinear_jogadores.removerFim();
                                System.out.println("(R) " + removido_lista.getNome());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (dados_linha_2[0].equals("R*")) {
                            try {
                                removido_lista = listalinear_jogadores.remover(Integer.parseInt(dados_linha_2[1]));
                                System.out.println("(R) " + removido_lista.getNome());
                            } catch (Exception e) {
//                    System.out.println(e.getMessage());
                            }

                        }
                    }

                    try {
                        listalinear_jogadores.mostrar();
                    } catch (Exception e) {
//                    System.out.println(e.getMessage());
                    }


                }
            }


        }


    }

}