//Samuel Fragoso - 902665
#include <stdio.h>
#include <stdlib.h>

int isFim(char* s){
    return(s[0]=='F'&&s[1]=='I'&&s[2]=='M');
}

void inverter(char* s){
    int n = 0;
    //contar o tamanho da string
    while(s[n] != '\0' && s[n] != '\n'){
        n++;
    }

    for (int i = 0; s[i] != '\0'; i++){
        char temp = s[i];
        s[i] = s[n-1-i];
        s[n-1-i] = temp;
    }
}

int main(){
    char linha[1000];
    while (fgets(linha, 1000, stdin) != NULL){
        if (isFim(linha)) break;
        
        inverter(linha);
        printf("%s\n", linha);
    }
    return 0;
}