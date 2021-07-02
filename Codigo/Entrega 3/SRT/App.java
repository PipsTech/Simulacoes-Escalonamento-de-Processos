import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		SRT srt = new SRT("SO_20_DadosEmpacotadeira_3.txt", "produtos.txt");
		srt.organizaTempoPacote();
		ThreadPiscina esteira1 = new ThreadPiscina(srt);
		ThreadPiscina esteira2 = new ThreadPiscina(srt);
		Thread a = new Thread(esteira1);
		Thread b = new Thread(esteira2);
		a.start();
		b.start();
		a.join();
		b.join();
		
		srt.printPedidos();
		System.out.println("Tempo Total: " + srt.getTempoTotal());
		System.out.println("Quantidade de pedidos atendidos: " + srt.getAtendidos());
	}
}
