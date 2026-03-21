//Samuel Fragoso - 902665
import java.util.Scanner;
import java.util.Random;


class Sorteio {
    //método iterativo que percorre a string e substitui todas as ocorrências
    //s: String original que será processada
    //letraAntiga: caractere a ser removido
    //letraNova: caractere que será inserido
    static String substituir(String s, char letraAntiga, char letraNova){
        //criar array de char do mesmo tamanho da string
        char[] resultado = new char[s.length()];

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            //se for a letra sorteada, troca
            //senão, mantém a original            
            if(c==letraAntiga){
                resultado[i] = letraNova;
            } else {
                resultado[i] = c;
            }
        }
        //tranforma o array em uma nova string
        //retorna a string com as substituições
        return new String(resultado);
    }

   public static void main(String[] args) {
    Random gerador = new Random();
    gerador.setSeed(4);

    Scanner sc = new Scanner(System.in, "UTF-8");
    while (sc.hasNextLine()){
        String linha = sc.nextLine();

        //condição de parada
        if(isFim(linha)) break;

        char letraAntiga = (char)('a'+(Math.abs(gerador.nextInt())%26));
        char letraNova   = (char)('a'+(Math.abs(gerador.nextInt())%26));

        System.out.println(substituir(linha, letraAntiga, letraNova));
    }
    sc.close();
    }

    //condição de parada, verificar se é igual a "FIM"
    //retorna true se a string for "FIM", false caso contrário
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}