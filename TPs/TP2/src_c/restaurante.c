#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

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
    Restaurante* r = (Restaurante*) malloc(sixeof(Restaurante));

    char colunas[12][512];
    int n = 0;

    char tmp[1024];
    strncpy(tmp,s,sizeof(tmp)-1);
}