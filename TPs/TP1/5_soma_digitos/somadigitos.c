//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

/**
 * Funcao recursiva que isola o ultimo digito e soma ao restante do numero.
 * Nao utiliza estruturas de repeticao, conforme as regras de recursividade.
 * @param n Numero inteiro positivo a ser processado.
 * @return A soma dos digitos do numero.
 */
int somarDigitosRecursivo(int n){
    int resultado;

    if (n==0){
        resultado = 0; //caso base, não há mais dígitos
    } else{
        //ultimo digito + soma do restante
        resultado = (n % 10) + somarDigitosRecursivo(n/10);
    }
    return resultado;
}

/**
 * Funcao de interface (chamada pelo main).
 * Trata o sinal do numero antes da recursao.
 * @param n Numero inteiro.
 * @return Resultado da soma.
 */
int somarDigitos(int n){
    if (n<0) n = -n;
    return somarDigitosRecursivo(n);
}

/**
 * Verifica se a string lida e igual a "FIM".
 * @param s Ponteiro para a string de entrada.
 * @return 1 se for "FIM", 0 caso contrario.
 */
int isFim(char* s) {
    // Verifica se os caracteres sao 'F', 'I' e 'M' nas posicoes 0, 1 e 2
    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M' && (s[3] == '\0' || s[3] == '\n' || s[3] == '\r'));
}

/**
 * Metodo principal para leitura e exibicao dos resultados.
 */
int main() {
    char linha[100]; 

    while (scanf("%s", linha) != EOF) {
        
        if (isFim(linha)) {
            break;
        }

        //converter a string para inteiro
        int num = atoi(linha); 
        
        //se o valor for vazio ou nao numero, atoi retorna 0 
        int resultado = somarDigitos(num);
        printf("%d\n", resultado);
    }

    return 0;
}