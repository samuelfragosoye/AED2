//Samuel Fragoso - 902665
import java.util.Scanner;

class ValidacaoSenha{
    /**
     * Verifica se a senha atende aos criterios de seguranca.
     * @param s Senha a ser validada.
     * @return true se for valida, false caso contrario.
     */
    public static boolean isValid(String s){
        if (s.length()<8) return false;

        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero    = false;
        boolean temEspecial  = false;

        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);

            if(c >= 'A' && c <= 'Z') temMaiuscula = true;
       else if(c >= 'a' && c <= 'z') temMinuscula = true;
       else if(c >= '0' && c <= '9') temNumero    = true;
       else temEspecial = true;
        }
        return temMaiuscula && temMinuscula && temNumero && temEspecial;
    }

    /**
    * Metodo principal para leitura e exibicao dos resultados.
    */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in, "UTF-8");
        while (sc.hasNextLine()){
            String linha = sc.nextLine();

            //condição de parada
            if (linha.length() == 3 && linha.charAt(0) == 'F' && 
                linha.charAt(1) == 'I' && linha.charAt(2) == 'M') break;
            
            if (isValid(linha)){
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }
        sc.close();
    }
}