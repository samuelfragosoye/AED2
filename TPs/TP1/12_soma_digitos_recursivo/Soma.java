//Samuel Fragoso - 902665
import java.util.Scanner;

class Soma {
    /**
     * Metodo recursivo que realiza a soma dos digitos.
     * @param n Numero inteiro positivo.
     * @return A soma dos digitos.
     */
    private static int somarDigitosRecursivo(int n){
        int resultado;
        if(n==0){
            resultado = 0;
        } else {
            resultado = (n % 10) + somarDigitosRecursivo(n/10);
        }
        return resultado;
    }

    /**
     * Metodo de interface para a soma de digitos.
     * Trata o valor absoluto para evitar problemas com numeros negativos.
     * @param n Numero lido da entrada.
     * @return Resultado da soma.
     */
    public static int somarDigitos(int n){
        return somarDigitosRecursivo(Math.abs(n));
    }

    /**
     * Verifica se a string e um numero inteiro valido.
     * @param s String de entrada.
     * @return true se for numerica, false caso contrario.
     */
    public static boolean isNumero(String s) {
        boolean valido = true;
        if (s == null || s.isEmpty()) {
            valido = false;
        } else {
            // Verifica cada caractere manualmente (Regra 9)
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // Permite sinal de menos apenas na primeira posicao
                if (i == 0 && c == '-') continue;
                if (c < '0' || c > '9') {
                    valido = false;
                    break;
                }
            }
        }
        return valido;
    }

    /**
     * Verifica se a string lida e o marcador "FIM".
     */
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    /**
     * Metodo principal para leitura e exibicao dos resultados.
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in, "UTF-8");
        while (sc.hasNextLine()){
            String entrada = sc.nextLine();

            if(isFim(entrada)){
                break;
            }

            if (isNumero(entrada)) {
                int num = Integer.parseInt(entrada);
                System.out.println(somarDigitos(num));
            } else {
                System.out.println(0);
            }
        }
        sc.close();
    }
}