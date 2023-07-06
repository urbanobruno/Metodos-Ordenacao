//import java.io.*;
//public class ArquivoTextoEscrita {
//    private BufferedWriter saida;
//    ArquivoTextoEscrita(String nomeArquivo) {
//        try {
//            saida = new BufferedWriter(new OutputStreamWriter(new
//                    FileOutputStream(nomeArquivo), "UTF-8"));
//        } catch (UnsupportedEncodingException excecao) {
//            System.out.println(excecao.getMessage());
//        } catch (IOException excecao) {
//            System.out.println("Erro na abertura do arquivo de escrita: " +
//                    excecao);
//        }
//    }
//    public void fecharArquivo() {
//        try {
//            saida.close();
//        }
//        catch (IOException excecao) {
//            System.out.println("Erro no fechamento do arquivo de escrita: " +
//                    excecao);
//        }
//    }
//    public void escrever(String textoEntrada) {
//        try {
//            saida.write(textoEntrada);
//            saida.newLine();
//        }
//        catch (IOException excecao){
//            System.out.println("Erro de entrada/saída " + excecao);
//        }
//    }
//}