//Samuel Fragoso
#include <stdio.h>
#include <stdlib.h>

/**
 * Funcao auxiliar para converter para minuscula manualmente.
 */
char toLower(char c){
    if(c >='A' && c <= 'Z') return c + 32;
    return c;
}

/**
 * Verifica recursivamente se a string contem apenas vogais.
 */
int apenasVogais(char* s, int i){
    if (s[i] == '\0' || s[i] == '\n' || s[i] == '\r') return 1;

    char c = toLower(s[i]);
    if(c == 'a' || c=='e' || c=='i' || c=='o'||c=='u'){
        return apenasVogais(s, i+1);
    }
    return 0;
}

/**
 * Verifica recursivamente se a string contem apenas consoantes.
 */
int apenasConsoantes(char* s, int i){
    if(s[i] == '\0' || s[i] == '\n' || s[i] == '\r') return 1;

    char c = toLower(s[i]);
    if ((c >= 'a' && c <= 'z') && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
        return apenasConsoantes(s, i+1);
    }   
    return 0;
}

/**
 * Verifica recursivamente se a string eh um numero inteiro.
 */
int isInteiro(char* s, int i){
    if(s[i] == '\0' || s[i] == '\n' || s[i] == '\r') return 1;

    if(s[i]>='0' && s[i]<='9'){
        return isInteiro(s, i+1);
    }
    return 0;
}

/**
 * Verifica recursivamente se a string eh um numero real.
 * @param pontos Contador de pontos ou virgulas encontrados.
 */
int isReal(char* s, int i, int pontos) {
    if (s[i] == '\0' || s[i] == '\n' || s[i] == '\r') return 1;
    
    if (s[i] >= '0' && s[i] <= '9') {
        return isReal(s, i + 1, pontos);
    } else if ((s[i] == '.' || s[i] == ',') && pontos == 0) {
        return isReal(s, i + 1, pontos + 1);
    }
    return 0;
}

/**
 * Verifica se a string eh "FIM".
 */
int isFim(char* s) {
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && (s[3] == '\0' || s[3] == '\n' || s[3] == '\r'));
}

/**
* Metodo principal para leitura e exibicao dos resultados.
*/
int main(){
    char linha[5000];
    while(fgets(linha, 5000, stdin)!=NULL){
        if(isFim(linha)) break;

        printf("%s ", apenasVogais(linha, 0) ? "SIM" : "NAO");
        printf("%s ", apenasConsoantes(linha, 0) ? "SIM" : "NAO");
        printf("%s ", isInteiro(linha, 0) ? "SIM" : "NAO");
        printf("%s\n", isReal(linha, 0, 0) ? "SIM" : "NAO");
    }
    return 0;
}