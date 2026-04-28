#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

//data
typedef struct{
    int ano;
    int mes;
    int dia;
} Data;

Data parse_data(char* s){
    Data d;
    sscanf(s,"%d-%d-%d", &d.ano, &d.mes, &d.dia);
    return d;
}

void formatar_data(Data* data, char* buffer){
    sprintf(buffer, "%02d/%02d/%04d", data->dia, data->mes, data->ano);
}

//hora
typedef struct{
    int hora;
    int minuto;
} Hora;

Hora parse_hora(char* s){
    Hora h;
    sscanf(s,"%d:%d", &h.hora, &h.minuto);
    return h;
}

void formatar_hora(Hora* hora, char* buffer){
    sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto);
}

//restaurante
#define MAX_TIPOS_COZINHA 10
#define MAX_NOME 100
#define MAX_CIDADE 100
#define MAX_TIPO 50

typedef struct {
    int id;
    char nome[MAX_NOME];
    char cidade[MAX_CIDADE];
    int capacidade;
    double avaliacao;
    char tipos_cozinha[MAX_TIPOS_COZINHA][MAX_TIPO];
    int n_tipos_cozinha;
    int faixa_preco;
    Hora horario_abertura;
    Hora horario_fechamento;
    Data data_abertura;
    bool aberto;
} Restaurante;

static int split(char* src, char delim, char dest[][MAX_TIPO], int max_partes){
    int count = 0;
    char tmp[512];
    strncpy(tmp,src,sizeof(tmp)-1);
    tmp[sizeof(tmp)-1] = '\0';

    char* token = strtok(tmp, &(char){delim});
    while (token != NULL && count < max_partes){
        strncpy(dest[count],token,MAX_TIPO - 1);
        dest[count][MAX_TIPO - 1] = '\0';
        count++;
        token = strtok(NULL, &(char){delim});
    }
    return count;
}

Restaurante* parse_restaurante(char* s){
    Restaurante* r = (Restaurante*) malloc(sizeof(Restaurante));

    char colunas[12][512];
    int n = 0;

    char tmp[1024];
    strncpy(tmp,s,sizeof(tmp)-1);
    tmp[sizeof(tmp)-1] = '\0';

    char* token = strtok(tmp,",");
    while (token != NULL && n < 12){
        strncpy(colunas[n],token,511);
        colunas[n][511] = '\0';
        n++;
        token = strtok(NULL, ",");
    }

    r->id = atoi(colunas[0]);
    strncpy(r->nome,   colunas[1], MAX_NOME -1);
    strncpy(r->cidade, colunas[2], MAX_CIDADE - 1);
    r->capacidade = atoi(colunas[3]);
    r->avaliacao  = atof(colunas[4]);

    r->n_tipos_cozinha = split(colunas[5], ';', r->tipos_cozinha, MAX_TIPOS_COZINHA);
    r->faixa_preco = (int) strlen(colunas[6]);

    char partes_horario[2][32];
    char hor_tmp[64];
    strncpy(hor_tmp, colunas[7], sizeof(hor_tmp)-1);
    char* h1 = strtok(hor_tmp, "-");
    char* h2 = strtok(NULL, "-");
    r->horario_abertura   = parse_hora(h1);
    r->horario_fechamento = parse_hora(h2);
    r->data_abertura      = parse_data(colunas[8]);
    r->aberto             = (strcmp(colunas[9], "true") == 0);

    return r;
}

void formatar_restaurante(Restaurante* r, char* buffer){
    char tipos_str[256] = "[";
    for (int i = 0; i<r->n_tipos_cozinha; i++){
        if(i>0) strcat(tipos_str, ",");
        strcat(tipos_str, r->tipos_cozinha[i]);
    }
    strcat(tipos_str, "]");

    char cifroes[8] = "";
    for(int i = 0; i < r->faixa_preco; i++) strcat(cifroes, "$");

    char buf_abertura[16], buf_fechamento[16], buf_data[16];
    formatar_hora(&r->horario_abertura,   buf_abertura);
    formatar_hora(&r->horario_fechamento, buf_fechamento);
    formatar_data(&r->data_abertura,      buf_data);

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %s-%s ## %s ## %s]",
        r->id, r->nome, r->cidade, r->capacidade, r->avaliacao,
        tipos_str, cifroes,
        buf_abertura, buf_fechamento,
        buf_data,
        r->aberto ? "true" : "false");
}

//colecao restaurante
typedef struct {
    int tamanho;
    Restaurante** restaurantes;
} Colecao_Restaurantes;

void ler_csv_colecao(Colecao_Restaurantes* colecao, char* path) {
    FILE* arquivo = fopen(path, "r");
    if (arquivo == NULL) {
        fprintf(stderr, "Arquivo não encontrado em: %s\n", path);
        return;
    }

    char linha[1024];
    fgets(linha, sizeof(linha), arquivo);

    colecao->tamanho = 0;
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        int len = strlen(linha);
        if (len > 0 && linha[len - 1] == '\n') linha[--len] = '\0';
        if (len > 0 && linha[len - 1] == '\r') linha[--len] = '\0';
        if (strlen(linha) == 0) continue;

        colecao->restaurantes[colecao->tamanho] = parse_restaurante(linha);
        colecao->tamanho++;
    }

    fclose(arquivo);
}

Colecao_Restaurantes* ler_csv() {
    Colecao_Restaurantes* colecao = (Colecao_Restaurantes*) malloc(sizeof(Colecao_Restaurantes));
    colecao->restaurantes = (Restaurante**) malloc(1000 * sizeof(Restaurante*));
    colecao->tamanho = 0;
    ler_csv_colecao(colecao, "/tmp/restaurantes.csv");
    return colecao;
}

typedef struct {
    Restaurante** itens;
    int topo;
    int capacidade;
} Pilha;

Pilha* criar_pilha(int cap) {
    Pilha* p = (Pilha*) malloc(sizeof(Pilha));
    p->itens = (Restaurante**) malloc(cap * sizeof(Restaurante*));
    p->topo = 0;
    p->capacidade = cap;
    return p;
}

void empilhar(Pilha* p, Restaurante* r) {
    if (p->topo < p->capacidade) {
        p->itens[p->topo++] = r;
    }
}

Restaurante* desempilhar(Pilha* p) {
    if (p->topo > 0) {
        return p->itens[--p->topo];
    }
    return NULL;
}

void gerar_log_counting(int comp, int mov, double tempo){
    FILE* arq = fopen("902665_pilha.txt", "w");
    if(arq != NULL){
        fprintf(arq, "902665\t%d\t%d\t%f",comp,mov,tempo);
        fclose(arq);
    }
}

 //main
 int main(){
    Colecao_Restaurantes* base = ler_csv();
    Pilha* pilha = criar_pilha(1000);
    int id;


    while (scanf("%d", &id) == 1 && id != -1) {
        for (int i = 0; i < base->tamanho; i++) {
            if (base->restaurantes[i]->id == id) {
                empilhar(pilha, base->restaurantes[i]);
                break;
            }
        }
    }

    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        char comando[3];
        scanf("%s", comando);

        if (strcmp(comando, "I") == 0) {
            scanf("%d", &id);
            for (int j = 0; j < base->tamanho; j++) {
                if (base->restaurantes[j]->id == id) {
                    empilhar(pilha, base->restaurantes[j]);
                    break;
                }
            }
        } else if (strcmp(comando, "R") == 0) {
            Restaurante* removido = desempilhar(pilha);
            if (removido != NULL) {
                printf("(R)%s\n", removido->nome); // Saída de remoção 
            }
        }
    }

    char buffer[1024];
    for (int i = pilha->topo - 1; i >= 0; i--) {
        formatar_restaurante(pilha->itens[i], buffer);
        printf("%s\n", buffer);
    }
    
    return 0;
 }