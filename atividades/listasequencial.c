#include <stdio.h>
#include <stdlib.h>

#define TAMANHO 100

//estrutura da lista sequencial
typedef struct {
    int array[TAMANHO];
    int n;
} Lista;

//inicializar a lista
void inicializar(Lista *lista){
    lista->n = 0;
}

// ==========================================
// INSERÇÕES
// ==========================================

//inserir no fim - O(1)
void inserirFim (Lista *lista, int x){
    if (lista->n >= TAMANHO){
        printf("Lista cheia!\n");
        exit(0);
    }
    lista->array[lista->n] = x;
    lista->n++;
}

//inserir no início
void inserirInicio(Lista *lista, int x){
    if (lista->n >= TAMANHO){
        printf("Lista cheia!\n");
        exit(0);
    }
    //desloca todos para a direita 
    for (int i = lista->n; i>0; i--){
        lista->array[i] = lista->array[i-1];
    } 
    lista->array[0] = x;
    lista->n++;
}

//inserir em posição específica
void inserir(Lista *lista, int x, int pos){
    if(lista->n >= TAMANHO){
        printf("Lista cheia!\n");
        exit(0);
    }
    //desloca elementos a partir de pos para a direita
    for (int i = lista->n; i>pos; i--){
        lista->array[i] = lista->array[i-1];
    }
    lista->array[pos] = x;
    lista->n++;
}

// ==========================================
// REMOÇÕES
// ==========================================

//remover do fim 
int removerFim(Lista *lista){
    if (lista->n==0){
        printf("Lista vazia!\n");
    }
    lista->n--;
    return lista->array[lista->n];
}

//remover do inicio
int removerInicio(Lista *lista){
    if (lista->n==0){
        printf("Lista vazia!\n");
        exit(0);
    }
    int resp = lista->array[0];
    lista->n--;
    //desloca todos para a esquerda
    for(int i =0; i<lista->n; i++){
        lista->array[i] = lista->array[i+1];
    }
    return resp;
}

//remover de posição especifica
int remover(Lista *lista, int pos){
    if (lista->n == 0 || pos < 0 || pos >= lista->n){
        printf("posição inválida ou lista vazia\n");
        exit(0);
    }
    int resp = lista->array[pos];
    lista->n--;
    //desloca para a esquerda
    for(int i = pos; i < lista->n; i++){
        lista->array[i] = lista->array[i+1];
    }
    return resp;
}

// ==========================================
// LISTA ORDENADA (crescente)
// ==========================================

//inserir mantendo ordem crescente
void inserirOrdenado(Lista *lista, int x){
    if(lista->n >= TAMANHO){
        printf("Lista cheia\n");
        exit(0);
    }
    int pos  = lista->n - 1;
    //desloca elementos maiores que x para a direita
    while(pos >= 0 && lista->array[pos] > x){
        lista->array[pos+1]=lista->array[pos];
        pos--;
    }
    //insere o x na posição
    lista->array[pos+1] = x;
    lista->n++;
}

//remove o menor da lista (ordenada)
int removerMenor(Lista *lista){
    return removerInicio(lista);
}

//remove o maior da lista (ordenada)
int removerMaior(Lista *lista){
    return removerFim(lista);
}

// ==========================================
// UTILITÁRIOS
// ==========================================

//mostrar a lista
void mostrar(Lista *lista) {
    printf("[ ");
    for (int i = 0; i < lista->n; i++) {
        printf("%d ", lista->array[i]);
    }
    printf("]\n");
}

//pesquisar
int pesquisar(Lista *lista, int x){
    for (int i = 0; i<lista->n; i++){
        if (lista->array[i]==x){
            return i;
        }
    }
    return -1;
}

// ==========================================
// MAIN — teste de tudo
// ==========================================

int main() {
    Lista lista;
    inicializar(&lista);

    printf("=== LISTA SEQUENCIAL ===\n\n");

    inserirInicio(&lista, 1);
    inserirFim(&lista, 7);
    inserirFim(&lista, 9);
    inserirInicio(&lista, 3);
    inserir(&lista, 8, 3);
    inserir(&lista, 4, 2);

    printf("Após inserções: ");
    mostrar(&lista);

    int x1 = removerInicio(&lista);
    int x2 = removerFim(&lista);
    int x3 = remover(&lista, 2);

    printf("Removidos: %d, %d, %d\n", x1, x2, x3);
    printf("Lista final: ");
    mostrar(&lista);

    printf("\n=== LISTA ORDENADA ===\n\n");

    Lista ord;
    inicializar(&ord);

    inserirOrdenado(&ord, 5);
    inserirOrdenado(&ord, 1);
    inserirOrdenado(&ord, 8);
    inserirOrdenado(&ord, 3);
    inserirOrdenado(&ord, 4);

    printf("Lista ordenada: ");
    mostrar(&ord);

    printf("Removendo o maior: %d\n", removerMaior(&ord));
    printf("Removendo o menor: %d\n", removerMenor(&ord));
    printf("Lista após remoções: ");
    mostrar(&ord);

    int idx = pesquisar(&ord, 4);
    printf("Pesquisa por 4: índice %d\n", idx);

    return 0;
}