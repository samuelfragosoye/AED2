#include <stdio.h>
#include <stdlib.h>

int isAnagrama(char* s1, char* s2){
    int contagem[256];
    //inicializa array com zeros
    for (int i = 0; i < 256; i++){
        contagem[i]=0;
    }

    int i = 0;
    while(s1[i] != '\0' && s2[i] != '\n'){
        contagem[(unsigned char)s2[i]]--;
        i++;
    }

    int j = 0;
    while (s2[j] != '\0' && s2[j] != '\n'){
        contagem[(unsigned char) s2[j]]--;
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