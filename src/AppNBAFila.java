import java.util.Scanner;
import java.lang.Math;

class Fila {

    private Jogador[] fila;
    private int frente;
    private int tras;
    private int tamanho;

    public Fila(int tamanho) {

        fila = new Jogador[tamanho];
        frente = 0;
        tras = 0;
        this.tamanho = tamanho;
    }

    public boolean filaVazia() {

        boolean resp;

        if (frente == tras)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public boolean filaCheia() {

        boolean resp;

        if (((tras + 1) % tamanho) == (frente % tamanho))
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void enfileirar(Jogador novo) throws Exception {

        int posicao;
        Jogador desenfileirado;

        if (!filaCheia()) {
            posicao = tras % tamanho;
            fila[posicao] = novo;
            tras++;
        } else {
            desenfileirado = this.desenfileirar();
            posicao = tras % tamanho;
            fila[posicao] = novo;
            tras++;
        }

    }

    public Jogador desenfileirar() throws Exception {

        Jogador desenfileirado;
        int posicao;

        if (!filaVazia()) {
            posicao = frente % tamanho;
            desenfileirado = fila[posicao];
            frente++;
            return desenfileirado;
        } else
            throw new Exception("Não foi possível desenfileirar nenhum elemento: a fila está vazia!");
    }

    public void mostrar() throws Exception {

        int posicao;
        if (!filaVazia()) {
            for (int i = frente; i < tras; i++) {
                posicao = i % tamanho;
                System.out.println("[" + posicao + "] ## " + fila[posicao].getId() + " ## " + fila[posicao].getNome() + " ## " + fila[posicao].getAltura() + " ## " + fila[posicao].getPeso() + " ## " + fila[posicao].getAnoNascimento() + " ## " + fila[posicao].getUniversidade() + " ## " + fila[posicao].getCidadeNascimento() + " ## " + fila[posicao].getEstadoNascimento() + "##");
            }
        } else
            throw new Exception("Não foi possível mostrar o conteúdo da fila: a fila está vazia!");
    }

    public double obterMediaAltura() throws Exception {
        int posicao;
        double alturas = 0;
        double media_altura;
        int count = 0;
        if (!filaVazia()) {
            for (int i = frente; i < tras; i++) {
                posicao = i % tamanho;
                alturas += fila[posicao].getAltura();
                count += 1;
            }

            media_altura = alturas / count;
            return Math.round(media_altura);
        } else
            throw new Exception("Não foi possível mostrar o conteúdo da fila: a fila está vazia!");

    }
}

public class AppNBAFila {
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
        fila_jogadores = new Fila(6);
        double altura_media_jogs = 0;

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
                    System.out.println((int) altura_media_jogs);
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
                                System.out.println((int) altura_media_jogs);
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


                }
            }


        }


    }

}