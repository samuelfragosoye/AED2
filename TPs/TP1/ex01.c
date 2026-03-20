#include <stdio.h>
#include <stdlib.h>

//recebe uma string e retorna ela criptografada
//chave 3
char* cifraCesar (char* s){
    for (int i = 0; s[i] != '\0'; i++){
        if (s[i]>= 'a' && s[i]<= 'z'){
            s[i] = (s[i]-'a' + 3) % 26 + 'a';
        }
    }
    printf("%s",s);
}

int main(){
    char linha [1000];
    scanf("%s", linha);
    cifraCesar(linha);
}