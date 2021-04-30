import java.io.IOException;

public class Aplicacao{

    public static void main(String[]args) throws IOException{
        FCFS p = new FCFS();
        p.leArquivo("SO_20_DadosEmpacotadeira1.txt");
        p.printPedidos();
        p.media();
    }
}
