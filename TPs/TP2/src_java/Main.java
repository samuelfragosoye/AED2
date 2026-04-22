import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
}

public class Main {
    public static void main(String[] args){
        ColecaoRestaurante baseDeDados = ColecaoRestaurante.lerCsv();
        Restaurante[] todos = baseDeDados.getRestaurantes();

        Scanner sc = new Scanner(System.in);
        int idProcurado = sc.nextInt();

        while (idProcurado != -1){
            for(int i = 0; i < baseDeDados.getTamanho(); i++){
                if(todos[i].getId() == idProcurado){
                    System.out.println(todos[i].formatar());
                    break;
                }
            }
            idProcurado = sc.nextInt();
        }
        sc.close();
    }
}