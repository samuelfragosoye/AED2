//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

/**
 * Funcao recursiva que altera cada caractere da string.
 * @param s Ponteiro para a string.
 * @param i Indice atual da recursao.
 */
void cifraRecursivo(char* s, int i){
    //caso base
    if(s[i]!='\0'&& s[i]!='\n' && s[i]!='\r'){
        s[i]=(char)(s[i]+3);
        cifraRecursivo(s, i+1);
    } else {
        s[i] = '\0';
    }
}

/**
 * Funcao de interface para a cifra de Cesar.
 * @param s String a ser cifrada.
 */
void cifrar(char* s) {
    cifraRecursivo(s, 0);
}

/**
 * Verifica se a string é "FIM".
 */
int isFim(char* s) {
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && (s[3] == '\0' || s[3] == '\n' || s[3] == '\r'));
}

/**
* Metodo principal para leitura e exibicao dos resultados.
*/
int main(){
    char linha[1000];

    while(fgets(linha, 1000, stdin)!=NULL){
        if(isFim(linha)) break;

        cifrar(linha);
        printf("%s\n", linha);
    }
    return 0;
}