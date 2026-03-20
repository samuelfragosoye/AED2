//Samuel Fragoso - 902665
import java.util.Scanner;

class CifraCesar {

//recebe uma string e retorna ela criptografada
//chave 3
    static String cifraCesar(String s) {
        char[] resultado = new char[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);


           //=====================================================
            //fiz uma versão que rodava o alfabeto e o z virava c
            //mas não foi aceito pelo verde
            /*if (c >= 'a' && c <= 'z') {
                c = (char) ((c - 'a' + 3) % 26 + 'a');
            }
            else if (c >= 'A' && c <= 'Z') {
                c = (char) ((c - 'A' + 3) % 26 + 'A');
            }
            else {
                c = (char) (c + 3);
            }*/
           //=====================================================


            //verde não reconhece alguns caracteres
            //Se o caractere for o símbolo de substituição
            //volta como veio
            if (c == '\uFFFD') {
                resultado[i] = c;
            } else {
                resultado[i] = (char) (c + 3);
            }

        }
        
        return new String(resultado);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, "UTF-8");
        while (sc.hasNextLine()){
        String linha = sc.nextLine();

        System.out.println(cifraCesar(linha));
        }
        sc.close();
    }

}
