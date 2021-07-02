import java.io.IOException;

public class Aplicacao {

	public static void main(String[]args) throws IOException, InterruptedException{
		FCFS s = new FCFS();
		//s.leArquivo("SO_20_DadosEmpacotadeira_2.txt");//o original do professor(Teria que adicionar o campo id Em cada uma das 319 linhas)
		s.leArquivo("pedidos.txt");//um  com 10 pedidos(ja com o campo id) so pra testar
		s.leArquivoProdutos("produtos.txt");//DPS VERIFICA SE  O NOME É ESSE
		s.orgFCFS();
        

		Thread t1 = new Thread(new Esteira(s));
		Thread t2 = new Thread(new Esteira(s));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		s.printPedidosAtendidos();

		System.out.println();
		s.printAteMeioDia();
		
		




	}

}
