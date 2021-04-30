import java.io.IOException;

public class Aplicacao{

    public static void main(String[]args) throws IOException{
        Prioridade p = new Prioridade();
        p.leArquivo("SO_20_DadosEmpacotadeira1.txt");
        p.printPedidos();
    }
}
