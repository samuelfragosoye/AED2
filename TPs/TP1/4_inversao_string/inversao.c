//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

/**
 * Funcao para verificar se a linha lida e o marcador de final de entrada.
 * @param s Ponteiro para a string a ser verificada.
 * @return 1 se for "FIM", 0 caso contrario.
 */
int isFim(char* s){
    return(s[0]=='F'&&s[1]=='I'&&s[2]=='M');
}

/**
 * Metodo iterativo para inverter uma string.
 * @param s Ponteiro para a string original.
 */
void inverter(char* s){
    int n = 0;
    //contar o tamanho da string
    while(s[n] != '\0' && s[n] != '\n'){
        n++;
    }
    s[n]='\0';

    //percorrer ate a metade trocando o caractere atual pelo seu oposto
    for (int i = 0; i < n / 2; i++){
        char temp = s[i];
        s[i] = s[n-1-i];
        s[n-1-i] = temp;
    }
}

int main(){
    char linha[1000];

    //leitura continua ate encontrar o fim do arquivo ou "FIM"
    while (fgets(linha, 1000, stdin) != NULL){
        if (isFim(linha)) break;
        
        inverter(linha);
        printf("%s\n", linha);
    }
    return 0;
}