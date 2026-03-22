//Samuel Fragoso - 902665
import java.util.Scanner;

class InversaoString {
    /**
     * Metodo recursivo que inverte os caracteres de um array.
     * @param array O array de caracteres da string.
     * @param i Indice inicial.
     * @param j Indice final.
     */
    private static void inverterRecursivo(char[] array, int i, int j){
        if (i<j){
            char temp = array[i];
            array[i]  = array[j];
            array[j]  = temp;

            inverterRecursivo(array, i+1, j-1);
        }
    }

    /**
     * Metodo de interface que prepara os dados para a recursao.
     * @param s String original.
     * @return String invertida.
     */
    public static String inverter(String s){
        char[] array = s.toCharArray();
        inverterRecursivo(array, 0, array.length -1);
        return new String(array);
    }

    /**
     * Verifica se a string lida eh "FIM".
     */
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
    * Metodo principal para leitura e exibicao dos resultados.
    */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in, "UTF-8");

        while(sc.hasNextLine()){
            String linha = sc.nextLine();

            if(isFim(linha)){
                break;
            }
            System.out.println(inverter(linha));
        }
        sc.close();
    }
}