import java.io.IOException;

public class Aplicacao {

	public static void main(String[]args) throws IOException, InterruptedException{
		FCFS s = new FCFS();
		s.leArquivo("teste.txt");
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
