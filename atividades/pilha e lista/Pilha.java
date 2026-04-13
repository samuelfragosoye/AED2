public class Pilha {
    private int[] dados;
    private int topo;
    private int capacidade;


//construtor
public Pilha(int capacidade){
    this.capacidade = capacidade;
    this.dados = new int[capacidade];
    this.topo = -1; //pilha vazia
}

//empilha um elemento
public void push(int valor){
    if(isFull()){
    System.out.println("Erro: pilha cheia");
    return;}
    dados[++topo] = valor;
}

//desempilha e retorna o topo
public int pop(){
    if (isEmpty()){
        System.out.println("Erro: pilha vazia");
        return -1;
    }
    return dados[topo--];
}

//retorna o topo sem remover
public int peek(){
    if (isEmpty()){
        System.out.println("Erro: pilha vazia");
        return -1;
    }
    return dados[topo];
}

public boolean isEmpty(){
    return topo == -1;
}

public boolean isFull(){
    return topo == capacidade - 1;
}

public int size(){
    return topo + 1;
}

//exibe a pilha
public void exibir(){
    if (isEmpty()){
        System.out.println("Pilha vazia");
        return;
    }
    System.out.print("Pilha [topo → base]: ");
    for(int i = topo; i>=0; i--){
        System.out.print(dados[i]+" ");
    }
    System.out.println();
}
}

public class Main {
    public static void main (String[] args){
        Pilha p = new Pilha(5);

        p.push(10); 
        p.push(20);        
        p.push(30);        
        p.exibir();
        
        System.out.println(p.peek()); //30 (não remove)
        System.out.println(p.pop());  //30 (remove)
        System.out.println(p.pop());  // 20
        p.exibir();                   //pilha [topo → base]: 10

        System.out.println(p.isEmpty()); //false
    }
}