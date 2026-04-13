public class Lista {
    private int[] dados;
    private int tamanho;
    private int capacidade;

    public Lista(int capacidade){
        this.capacidade = capacidade;
        this.dados = new int[capacidade];
        this.tamanho = 0 //lista vazia
    }

    //insere no final
    public void inserirFinal(int valor){
        if(isFull()){
            System.out.println("Erro:lista cheia");
            return;
        }
        dados[tamanho] = valor;
        tamanho++;
    }

    //insere em posição específica
    public void inserir(int valor, int posicao){
        if(isFull()){
            System.out.println("Erro: lista cheia");
            return;
        }
        if(posicao < 0 || posicao > tamanho){
            System.out.println("Erro: posicao invalida");
            return;
        }
        //desloca para a direita
        for (int i = tamanho; i > posicao; i++){
            dados[i]=dados[i+1];
        }
        dados[posicao] = valor;
        tamanho++;
    } 
    
    //remove de uma posicao específica
    public int remover(int posicao){
        if (isEmpty()){
            System.out.println("Erro: lista vazia!");
            return -1;
        }
        if (posicao < 0 || posicao >= tamanho) {
            System.out.println("Erro: posição inválida!");
            return -1;
        }
        int removido = dados[posicao];
        //desloca todos para a esquerda
        for (int i = posicao; i < tamanho -1; i++){
            dados[i] = dados[i+1];
        }
        tamanho--;
        return removido;
    }

    //retorna o índice do valor
    public int buscar(int valor){
        for(int i = 0; i<tamanho; i++){
            if(dados[i]==valor) return i;
        }
        return -1;
    }

    public boolean isEmpty() { return tamanho == 0; }
    public boolean isFull()  { return tamanho == capacidade; }
    public int size()        { return tamanho; }

    public void exibir(){
        if(isEmpty()){
            System.out.println("Lista vazia");
            return;
        }
        System.out.println("Lista: ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(dados[i]);
            if (i < tamanho - 1) System.out.print(" → ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main (String[] args){
        Lista l = new Lista(5);

        l.inserirFinal(10);
        l.inserirFinal(20);
        l.inserirFinal(30);
        l.exibir();           //Lista: 10 → 20 → 30

        l.inserir(99,1);      //insere 99 na posicao 1
        l.exibir();           //Lista: 10 → 99 → 20 → 30

        System.out.println(l.remover(1)); // remove posição 1 → retorna 99
        l.exibir();           //Lista: 10 → 20 → 30  

        System.out.println(l.buscar(20)); // → 1 (índice)
        System.out.println(l.buscar(99)); // → -1 (não encontrado)
    }
}