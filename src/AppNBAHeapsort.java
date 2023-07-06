import java.io.*;
import java.util.Scanner;

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

public class AppNBAHeapsort {

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

        AppNBAHeapsort zs = new AppNBAHeapsort();

        zs.sort(listaJogadores, count_tam);

        int z;
        for (z = 0; z < count_tam; z++) {
            listaJogadores[z].imprime();
        }


    }

    void sort(Jogador[] array, int n) {

        // Criando outro vetor, com todos os elementos do vetor anterior reposicionados (uma posição a frente)
        // de forma a ignorar a posição zero
        Jogador[] tmp = new Jogador[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
        }

        //Contrução do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            constroi(tmp, tamHeap);
        }

        //Ordenação propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            troca(tmp, 1, tamHeap--);
            reconstroi(tmp, tamHeap);
        }

        //Alterar o vetor para voltar à posição zero
        for (int i = 0; i < n; i++) {
            array[i] = tmp[i + 1];
        }
    }

    void constroi(Jogador[] array, int tamHeap) {

        for (int i = tamHeap; i > 1 && (array[i].getAltura() > array[i / 2].getAltura() || (array[i].getAltura() == array[i / 2].getAltura() && array[i].getNome().compareTo(array[i / 2].getNome()) > 0)); i /= 2) {
            troca(array, i, i / 2);
        }
    }

    void reconstroi(Jogador[] array, int tamHeap) {

        int i = 1;

        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(array, i, tamHeap);
            if (array[i].getAltura() < array[filho].getAltura() || (array[i].getAltura() == array[filho].getAltura() && array[i].getNome().compareTo(array[filho].getNome()) < 0)) {
                troca(array, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    int getMaiorFilho(Jogador[] array, int i, int tamHeap) {

        int filho;

        if (2 * i == tamHeap || (array[2 * i].getAltura() > array[2 * i + 1].getAltura() || (array[2 * i].getAltura() == array[2 * i + 1].getAltura() && array[2 * i].getNome().compareTo(array[2 * i + 1].getNome()) > 0))) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    void troca(Jogador[] array, int i, int j) {

        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}