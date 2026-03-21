//Samuel Fragoso - 902665
import java.util.Scanner;

class Is{
    /**
    * Verifica se a string e composta apenas por vogais.
    * @param s String a ser avaliada.
    * @return true se tiver apenas vogais, false caso contrario.
    */
    static boolean isVogal(String s){
        boolean resp = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
               if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' &&
                   c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U'){
                   resp = false;
               } 
        }
        return resp;
    }

    /**
     * Verifica se a string e composta apenas por vogais.
     * @param s String a ser avaliada.
     * @return true se tiver apenas vogais, false caso contrario.
     */
    static boolean isConsoante(String s){
        boolean resp = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean isLetra = (c >='a' && c <= 'z') || (c >= 'A' && c <= 'Z');

            boolean isVogal = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
            
            if (!isLetra || isVogal) {
            return false; //se não for letra ou se for vogal, já não é só consoante
        }                   

        }
        return resp;
    }
    /**
     * Verifica se a string e um numero inteiro.
     * @param s String a ser avaliada.
     * @return true se for inteiro, false caso contrario.
     */
    static boolean isInteiro(String s){
        boolean resp = true;
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if(c < '0' || c > '9'){
                resp = false;
            }
        }
        return resp;
    }

    /**
     * Verifica se a string e um numero real.
     * @param s String a ser avaliada.
     * @return true se for real, false caso contrario.
     */
    static boolean isReal(String s){
        int virgulasOuPontos = 0;
        boolean resp = true;
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                continue; 
            } else if (c == ',' || c == '.') {
                virgulasOuPontos++;
            } else {
                return false; //caractere invalido (letra, etc)
            }
        }
        return virgulasOuPontos <= 1;
    }

    //metodo auxiliar para converter boolean em SIM/NAO
    static String simNao(boolean b){
        return b ? "SIM" : "NAO";
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in, "UTF-8");
        while (sc.hasNextLine()){
        String linha = sc.nextLine();
        if (linha.length() == 3 && linha.charAt(0) == 'F' && 
            linha.charAt(1) == 'I' && linha.charAt(2) == 'M') break;

        // Imprime os 4 resultados na mesma linha 
        System.out.print(simNao(isVogal(linha)) + " ");
        System.out.print(simNao(isConsoante(linha)) + " ");
        System.out.print(simNao(isInteiro(linha)) + " ");
        System.out.println(simNao(isReal(linha)));    
        }
        sc.close();
    }
}