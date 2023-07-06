import java.io.*;
import java.util.Scanner;

class No {

    private Jogador item;
    private No esquerda;
    private No direita;

    public No() {

        item = new Jogador();
        esquerda = null;
        direita = null;
    }

    public No(Jogador registro) {

        item = registro;
        esquerda = null;
        direita = null;
    }

    public Jogador getItem() {
        return item;
    }

    public void setItem(Jogador item) {
        this.item = item;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}

class ABB {

    private No raiz;

    public ABB() {

        raiz = null;
    }

    // chave: 19
    public Jogador pesquisar(String chave) {
        return pesquisar(this.raiz, chave);
    }

    // 1.a execução:
    // raizSubarvore: 16
    // chave: 19
    // retorna o resultado da 2.a execução do método pesquisar: 19
    // 2.a execução:
    // raizSubarvore: 23
    // chave: 19
    // retorna o resultado da 3.a execução do método pesquisar: 19
    // 3.a execução:
    // raizSubarvore: 19
    // chave: 19
    // retorna: 19
    private Jogador pesquisar(No raizSubarvore, String chave) {

        if (raizSubarvore == null)
            return null;
        else if (chave.equals(raizSubarvore.getItem().getNome())) {
            System.out.print(raizSubarvore.getItem().getNome() + " ");
            return raizSubarvore.getItem();
        } else if (chave.compareTo(raizSubarvore.getItem().getNome()) > 0) {
            System.out.print(raizSubarvore.getItem().getNome() + " ");
            return pesquisar(raizSubarvore.getDireita(), chave);
        } else {
            System.out.print(raizSubarvore.getItem().getNome() + " ");
            return pesquisar(raizSubarvore.getEsquerda(), chave);
        }
    }

    // novo: 11
    // atribuir à raiz da árvore o retorno da 1.a execução do método inserir: 16
    public void inserir(Jogador novo) throws Exception {
        this.raiz = inserir(this.raiz, novo);
    }

    // 1.a execução:
    // raizSubarvore: 16
    // novo: 11
    // atribuir à subárvore esquerda do 16 o retorno da 2.a execução do método inserir: 8
    // retorno: 16
    // 2.a execução:
    // raizSubarvore: 8
    // novo: 11
    // atribuir à subárvore direita do 8 o retorno da 3.a execução do método inserir: 11
    // retorno: 8
    // 3.a execução:
    // raizSubarvore: null
    // novo: 11
    // retorno: 11
    private No inserir(No raizSubarvore, Jogador novo) throws Exception {

        if (raizSubarvore == null)
            raizSubarvore = new No(novo);
        else if (novo.getId() == raizSubarvore.getItem().getId())
            throw new Exception("Não foi possível inserir o item na árvore: chave já inseriada anteriormente!");
        else if (novo.getNome().compareTo(raizSubarvore.getItem().getNome()) < 0)
            raizSubarvore.setEsquerda(inserir(raizSubarvore.getEsquerda(), novo));
        else
            raizSubarvore.setDireita(inserir(raizSubarvore.getDireita(), novo));

        return raizSubarvore;
    }

    // chaveRemover: 19
    // raiz = retorno da 1.a execução do método remover --> 16
    public void remover(int chaveRemover) throws Exception {
        this.raiz = remover(this.raiz, chaveRemover);
    }

    // 1.a execução:
    // raizSubarvore: 16
    // chaveRemover: 19
    // subárvore à direita do 16 = retorno da 2.a execução do método remover --> 23
    // retorna: 16
    // 2.a execução:
    // raizSubarvore: 23
    // chaveRemover: 19
    // subárvore à esquerda do 23 = retorno da 3.a execução do método remover --> null
    // retorna: 23
    // 3.a execução:
    // raizSubarvore: 19
    // chaveRemover: 19
    // retorna: null
    private No remover(No raizSubarvore, int chaveRemover) throws Exception {

        if (raizSubarvore == null)
            throw new Exception("Não foi possível remover o item da árvore: chave não encontrada!");
        else if (chaveRemover == raizSubarvore.getItem().getId()) {
            if (raizSubarvore.getEsquerda() == null)
                raizSubarvore = raizSubarvore.getDireita();
            else if (raizSubarvore.getDireita() == null)
                raizSubarvore = raizSubarvore.getEsquerda();
            else
                raizSubarvore.setEsquerda(antecessor(raizSubarvore, raizSubarvore.getEsquerda()));
        } else if (chaveRemover > raizSubarvore.getItem().getId())
            raizSubarvore.setDireita(remover(raizSubarvore.getDireita(), chaveRemover));
        else
            raizSubarvore.setEsquerda(remover(raizSubarvore.getEsquerda(), chaveRemover));

        return raizSubarvore;
    }

    private No antecessor(No noRetirar, No raizSubarvore) {

        if (raizSubarvore.getDireita() != null)
            raizSubarvore.setDireita(antecessor(noRetirar, raizSubarvore.getDireita()));
        else {
            noRetirar.setItem(raizSubarvore.getItem());
            raizSubarvore = raizSubarvore.getEsquerda();
        }

        return raizSubarvore;
    }

    public void caminhamentoEmOrdem() {
        caminhamentoEmOrdem(this.raiz);
    }

    // 1.a execução: raizSubarvore: 16
    // parou imprimindo a subárvore esquerda
    // 2.a execução: raizSubarvore: 8
    // parou imprimindo a subárvore direita
    // 3.a execução: raizSubarvore: 4
    // parou imprimindo a subárvore direita --> finalizada
    // 4.a execução: raizSubarvore: null --> finalizada
    // 5.a execução: raizSubarvore: 5
    // parou imprimindo a subárvore direita --> finalizada
    // 6.a execução: raizSubarvore: null --> finalizada
    // 7.a execução: raizSubarvore: null --> finalizada
    // 8.a execução: raizSubarvore: 11
    private void caminhamentoEmOrdem(No raizSubarvore) {

        if (raizSubarvore != null) {
            caminhamentoEmOrdem(raizSubarvore.getEsquerda());
            raizSubarvore.getItem().imprime();
            caminhamentoEmOrdem(raizSubarvore.getDireita());
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

public class AppNBAArvoreBinariaBusca {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Jogador[] listaJogadores = new Jogador[3922];

        ArquivoTextoLeitura arqLeitura;
        String linha;

        arqLeitura = new ArquivoTextoLeitura("/tmp/jogadores.txt");
        linha = arqLeitura.ler();
        linha = arqLeitura.ler();

        while (linha != null) {

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


            linha = arqLeitura.ler();
        }

        Jogador removido_lista;
        ABB minhaArvore = new ABB();
        int off = 0;
        int off2 = 0;

        while (sc.hasNextLine()) {

            // antes FIM
            String linha_lida_console = sc.nextLine();
            if (linha_lida_console.equals("FIM")) {
                off2 = 1;
            }

            if (off2 == 0) {
                int id_lido = Integer.parseInt(linha_lida_console);
                try {
                    minhaArvore.inserir(listaJogadores[id_lido]);
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

                        Jogador achou = minhaArvore.pesquisar(linha_lida_console);
                        if (achou == null) {
                            System.out.print("NAO");
                        } else {
                            System.out.print("SIM");
                        }

                        System.out.println();

                        linha_lida_console = sc.nextLine();

                    }

                    break;

                }
            }


        }


    }

}