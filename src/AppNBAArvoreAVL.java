import java.util.Scanner;

class No {

    private Jogador item;
    private int altura;
    private No esquerda;
    private No direita;

    public No() {

        item = new Jogador();
        altura = 0;
        esquerda = null;
        direita = null;
    }

    public No(Jogador registro) {

        item = registro;
        altura = 0;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura() {

        int alturaEsq, alturaDir;

        if (this.direita == null)
            alturaDir = -1;
        else
            alturaDir = this.direita.getAltura();

        if (this.esquerda == null)
            alturaEsq = -1;
        else
            alturaEsq = this.esquerda.getAltura();

        if (alturaEsq > alturaDir)
            this.altura = alturaEsq + 1;
        else
            this.altura = alturaDir + 1;
    }

    public int getFatorBalanceamento() {

        int alturaEsq, alturaDir;

        if (this.direita == null)
            alturaDir = -1;
        else
            alturaDir = this.direita.getAltura();

        if (this.esquerda == null)
            alturaEsq = -1;
        else
            alturaEsq = this.esquerda.getAltura();

        return (alturaEsq - alturaDir);
    }
}

class AVL {

    private No raiz;

    public AVL() {

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

        return balancear(raizSubarvore);
    }

    private No balancear(No raizSubarvore) {

        int fatorBalanceamento;
        int fatorBalanceamentoFilho;

        fatorBalanceamento = raizSubarvore.getFatorBalanceamento();

        if (fatorBalanceamento == 2) {
            // árvore desbalanceada para a esquerda.
            fatorBalanceamentoFilho = raizSubarvore.getEsquerda().getFatorBalanceamento();
            if (fatorBalanceamentoFilho == -1) {
                // rotação dupla.
                // rotação do filho à esquerda.
                raizSubarvore.setEsquerda(rotacionarEsquerda(raizSubarvore.getEsquerda()));
            }
            // rotação à direita.
            raizSubarvore = rotacionarDireita(raizSubarvore);
        } else if (fatorBalanceamento == -2) {
            // árvore desbalanceada para a direita.
            fatorBalanceamentoFilho = raizSubarvore.getDireita().getFatorBalanceamento();
            if (fatorBalanceamentoFilho == 1) {
                // rotação dupla.
                // rotação do filho à direita.
                raizSubarvore.setDireita(rotacionarDireita(raizSubarvore.getDireita()));
            }
            // rotação à esquerda.
            raizSubarvore = rotacionarEsquerda(raizSubarvore);
        } else
            raizSubarvore.setAltura();

        return raizSubarvore;
    }

    private No rotacionarDireita(No p) {

        No u = p.getEsquerda();
        No filhoEsquerdaDireita = u.getDireita();  // triângulo vermelho

        u.setDireita(p);
        p.setEsquerda(filhoEsquerdaDireita);

        p.setAltura();
        u.setAltura();

        return u;
    }

    private No rotacionarEsquerda(No p) {

        No z = p.getDireita();
        No filhoDireitaEsquerda = z.getEsquerda();  // triângulo vermelho

        z.setEsquerda(p);
        p.setDireita(filhoDireitaEsquerda);

        p.setAltura();
        z.setAltura();

        return z;
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

        return balancear(raizSubarvore);
    }

    private No antecessor(No noRetirar, No raizSubarvore) {

        if (raizSubarvore.getDireita() != null)
            raizSubarvore.setDireita(antecessor(noRetirar, raizSubarvore.getDireita()));
        else {
            noRetirar.setItem(raizSubarvore.getItem());
            raizSubarvore = raizSubarvore.getEsquerda();
        }

        return balancear(raizSubarvore);
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

public class AppNBAArvoreAVL {
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
        AVL minhaArvore = new AVL();
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