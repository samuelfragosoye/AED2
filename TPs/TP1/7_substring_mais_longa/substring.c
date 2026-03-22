//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

/**
 * Calcula o comprimento da maior substring sem caracteres repetidos.
 * @param s Ponteiro para a string.
 * @return O tamanho da maior substring encontrada.
 */
int substringMaisLonga(char* s){
    int n = 0;
    
    while(s[n] != '\0' && s[n]!='\n'&&s[n]!='\r') n++;

    int maxLen = 0;
    int inicio = 0;
    int ultimaPosicao[256];

    for(int i = 0; i<256; i++){
        ultimaPosicao[i] = -1;
    }

    for(int fim =0; fim<n; fim++){
        unsigned char c = (unsigned char)s[fim];
        if (ultimaPosicao[c]>=inicio){
            inicio = ultimaPosicao[c] + 1;
        }
        ultimaPosicao[c] =fim;

        int tamanhoAtual = fim - inicio + 1;
        if(tamanhoAtual >maxLen){
            maxLen = tamanhoAtual;
        }
    }
    return maxLen;
}

/**
 * Verifica se a string lida e o marcador de final de entrada "FIM".
 */
int isFim(char* s) {
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && (s[3] == '\0' || s[3] == '\n' || s[3] == '\r'));
}

/**
 * Metodo principal para leitura e exibicao dos resultados.
 */
int main(){
    char linha[1000];
    while (fgets(linha, 1000, stdin) != NULL){
        if(isFim(linha)) break;
        int resultado = substringMaisLonga(linha);
        printf("%d\n", resultado);
    }
    return 0;
}