//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

//função auxiliar para converter para minúscula manualmente
char paraMinuscula(char c) {
    if (c >= 'A' && c <= 'Z') return c + 32;
    return c;
}

/**
 * Verifica se duas strings sao anagramas.
 * @param s1 Primeira string.
 * @param s2 Segunda string.
 * @return 1 para SIM, 0 para NAO.
 */

int isAnagrama(char* s1, char* s2){
    int contagem[256];
    //inicializa array com zeros
    for (int i = 0; i < 256; i++){
        contagem[i]=0;
    }

    int i = 0;
    while(s1[i] != '\0' && s1[i] != '\n'){
        //converte para minuscula antes de contar
        char c1 = paraMinuscula(s1[i]);
        contagem[(unsigned char)c1]++; 
        i++; 
    }

    int j = 0;
    while (s2[j] != '\0' && s2[j] != '\n'){
        //converte para minuscula antes de subtrair
        char c2 = paraMinuscula(s2[j]);
        contagem[(unsigned char)c2]--;
        j++;
    }

    //se os tamanhos forem diferentes não é anagrama
    if(i != j) return 0;

    //verifica se o array esta todo zeradp
    for (int k = 0; k<256; k++){
        if(contagem[k] != 0){
            return 0;
        }
    }
    return 1;
}

/**
 * Verifica se a string lida eh o marcador de final de entrada "FIM".
 * @param s Ponteiro para a string.
 * @return 1 se for FIM, 0 caso contrario.
 */
int isFim(char* s) {
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && (s[3] == '\0' || s[3] == '\n' || s[3] == '\r'));
}

/**
 * Metodo principal para leitura e exibicao dos resultados.
 */
int main() {
    char s1[500];
    char s2[500];

    while (scanf("%s", s1) != EOF) {
        if (isFim(s1)) break;

        scanf("%s", s2);  // Le a segunda palavra diretamente

        if (isAnagrama(s1, s2)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }
    return 0;
}