import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data (int dia, int mes, int ano){
        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
    }

    public int getAno(){return ano;}
    public int getMes(){return mes;}
    public int getDia(){return dia;}


    public static Data parseData(String s){
        Scanner sc = new Scanner(s);
        sc.useDelimiter("-");
        int ano = sc.nextInt();
        int mes = sc.nextInt();
        int dia = sc.nextInt();
        sc.close();

        Data data = new Data(dia,mes,ano);

        return data;
    }

    public String formatar(){
        String s = String.format("%02d/%02d/%04d", dia, mes, ano);
        return s;
    }
}

class Hora {
    private int hora;
    private int minuto;

    public Hora (int hora, int minuto){
        this.hora = hora;
        this.minuto=minuto;
    }

    public int getHora(){return hora;}
    public int getMinuto(){return minuto;}

    public static Hora parseHora(String s){
        Scanner sc = new Scanner(s);
        sc.useDelimiter(":");
        int hora   = sc.nextInt();
        int minuto = sc.nextInt();
        sc.close();

        Hora h = new Hora(hora,minuto);

        return h;
    }

    public String formatar(){
        String s = String.format("%02d:%02d", hora, minuto);
        return s;
    }

}

class Restaurante {
    private int id;
    private String nome;
    private String cidade;
    private int capacidade;
    private double avaliacao;
    private String[] tiposCozinha;
    private int faixaPreco;
    private Hora horarioAbertura;
    private Hora horarioFechamento;
    private Data dataAbertura;
    private boolean aberto;
    
    public Restaurante(int id, String nome, String cidade, int capacidade,
                       double avaliacao, String[] tiposCozinha, int faixaPreco,
                       Hora horarioAbertura, Hora horarioFechamento, 
                       Data dataAbertura, boolean aberto) {
        this.id                = id;
        this.nome              = nome;
        this.cidade            = cidade;
        this.capacidade        = capacidade;
        this.avaliacao         = avaliacao;
        this.tiposCozinha      = tiposCozinha;
        this.faixaPreco        = faixaPreco;
        this.horarioAbertura   = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.dataAbertura      = dataAbertura;
        this.aberto            = aberto;               
    }
    
    public int getId(){return id;}
    public String getNome(){return nome;}
    public String getCidade(){return cidade;}
    public int getCapacidade(){return capacidade;}
    public double getAvaliacao(){return avaliacao;}
    public String[] getTiposCozinha(){return tiposCozinha;}
    public int getFaixaPreco(){return faixaPreco;}
    public Hora getHorarioAbertura(){return horarioAbertura;}
    public Hora getHorarioFechamento(){return horarioFechamento;}
    public Data getDataAbertura(){return dataAbertura;}
    public boolean getAberto(){return aberto;}
    
    public static Restaurante parseRestaurante(String s){
        String[] colunas = s.split(",");
        
        int id                 = Integer.parseInt(colunas[0]);
        String nome            = colunas[1];
        String cidade          = colunas[2];
        int capacidade         = Integer.parseInt(colunas[3]);
        double avaliacao       = Double.parseDouble(colunas[4]);
        String[] tiposCozinha  = colunas[5].split(";");
        int faixaPreco         = colunas[6].length();
        String[] partesHorario = colunas[7].split("-");
        Hora horarioAbertura   = Hora.parseHora(partesHorario[0]);
        Hora horarioFechamento = Hora.parseHora(partesHorario[1]);
        Data dataAbertura      = Data.parseData(colunas[8]);
        boolean aberto         = Boolean.parseBoolean(colunas[9]);
        
        return new Restaurante(id, nome, cidade, capacidade, avaliacao,
                               tiposCozinha, faixaPreco, horarioAbertura,
                               horarioFechamento, dataAbertura, aberto);
    }

    public String formatar() {
        String tiposStr = "[" + String.join(",", tiposCozinha) + "]";
        String cifroes  = "$".repeat(faixaPreco);

        return String.format("[%d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %s-%s ## %s ## %b]",
            id, nome, cidade, capacidade, avaliacao, tiposStr, cifroes,
            horarioAbertura.formatar(), horarioFechamento.formatar(),
            dataAbertura.formatar(), aberto);
    }   
}

class ColecaoRestaurante {
    private Restaurante[] restaurantes;
    private int tamanho;

    public ColecaoRestaurante(int capacidadeMax){
        this.restaurantes = new Restaurante[capacidadeMax];
        this.tamanho = 0;
    }

    public int getTamanho(){return tamanho;}
    public Restaurante[] getRestaurantes(){return restaurantes;}

    public void adicionar(Restaurante r) {
        if (tamanho < restaurantes.length) {
            restaurantes[tamanho++] = r;
        }
    }

    public void lerCsv(String path){
        try{
            File arquivo = new File(path);
            Scanner leitor = new Scanner(arquivo);

            if (leitor.hasNextLine()) leitor.nextLine();

            while(leitor.hasNextLine() && tamanho < restaurantes.length){
                String linha = leitor.nextLine();
                if (!linha.isEmpty()){
                    restaurantes[tamanho] = Restaurante.parseRestaurante(linha);
                    tamanho++;
                }
            }
            leitor.close();
        } catch (FileNotFoundException e){
            System.err.println("Arquivo não encontrado em: "+ path);
        }
    }
    public static ColecaoRestaurante lerCsv() {
        ColecaoRestaurante colecao = new ColecaoRestaurante(1000);
        colecao.lerCsv("/tmp/restaurantes.csv");
        return colecao;
    }

public void inserirInicio(Restaurante r) {
    for (int i = tamanho; i > 0; i--)
        restaurantes[i] = restaurantes[i - 1];
    restaurantes[0] = r;
    tamanho++;
}

public void inserir(Restaurante r, int posicao) {
    for (int i = tamanho; i > posicao; i--)
        restaurantes[i] = restaurantes[i - 1];
    restaurantes[posicao] = r;
    tamanho++;
}

public void inserirFim(Restaurante r) {
    restaurantes[tamanho++] = r;
}

public Restaurante removerInicio() {
    Restaurante r = restaurantes[0];
    for (int i = 0; i < tamanho - 1; i++)
        restaurantes[i] = restaurantes[i + 1];
    tamanho--;
    return r;
}

public Restaurante remover(int posicao) {
    Restaurante r = restaurantes[posicao];
    for (int i = posicao; i < tamanho - 1; i++)
        restaurantes[i] = restaurantes[i + 1];
    tamanho--;
    return r;
}

public Restaurante removerFim() {
    return restaurantes[--tamanho];
}
}

public class Lista {
    public static void main(String[] args) {
    ColecaoRestaurante baseDeDados = ColecaoRestaurante.lerCsv();
    ColecaoRestaurante lista = new ColecaoRestaurante(2000);
    Scanner sc = new Scanner(System.in);

    while (sc.hasNextInt()) {
        int id = sc.nextInt();
        if (id == -1) break;
        for (int i = 0; i < baseDeDados.getTamanho(); i++) {
            if (baseDeDados.getRestaurantes()[i].getId() == id) {
                lista.inserirFim(baseDeDados.getRestaurantes()[i]);
                break;
            }
        }
    }

    int n = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < n; i++) {
        String linha = sc.nextLine().trim();
        String[] partes = linha.split(" ");
        String cmd = partes[0];

        if (cmd.equals("II")) {
            int id = Integer.parseInt(partes[1]);
            for (int j = 0; j < baseDeDados.getTamanho(); j++) {
                if (baseDeDados.getRestaurantes()[j].getId() == id) {
                    lista.inserirInicio(baseDeDados.getRestaurantes()[j]);
                    break;
                }
            }
        } else if (cmd.equals("I*")) {
            int pos = Integer.parseInt(partes[1]);
            int id  = Integer.parseInt(partes[2]);
            for (int j = 0; j < baseDeDados.getTamanho(); j++) {
                if (baseDeDados.getRestaurantes()[j].getId() == id) {
                    lista.inserir(baseDeDados.getRestaurantes()[j], pos);
                    break;
                }
            }
        } else if (cmd.equals("IF")) {
            int id = Integer.parseInt(partes[1]);
            for (int j = 0; j < baseDeDados.getTamanho(); j++) {
                if (baseDeDados.getRestaurantes()[j].getId() == id) {
                    lista.inserirFim(baseDeDados.getRestaurantes()[j]);
                    break;
                }
            }
        } else if (cmd.equals("RI")) {
            Restaurante r = lista.removerInicio();
            System.out.println("(R)" + r.getNome());
        } else if (cmd.equals("R*")) {
            int pos = Integer.parseInt(partes[1]);
            Restaurante r = lista.remover(pos);
            System.out.println("(R)" + r.getNome());
        } else if (cmd.equals("RF")) {
            Restaurante r = lista.removerFim();
            System.out.println("(R)" + r.getNome());
        }
    }

    for (int i = 0; i < lista.getTamanho(); i++)
        System.out.println(lista.getRestaurantes()[i].formatar());

    sc.close();
}
    }
