import java.io.IOException;

public class tp{

    public static void main(String[]args) throws IOException{
        Sjf p = new Sjf();
        p.leArquivo("SO_20_DadosEmpacotadeira1.txt");
        p.printPedidos();
    }
}
