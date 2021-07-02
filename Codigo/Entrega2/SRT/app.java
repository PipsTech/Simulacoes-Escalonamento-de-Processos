import java.io.IOException;

public class app {
	public static void main(String[] args) throws IOException, InterruptedException {
		SRT srt = new SRT();
		srt.lerArquivo("texto.txt");
		ThreadPiscina esteira1 = new ThreadPiscina(srt);
		ThreadPiscina esteira2 = new ThreadPiscina(srt);
		Thread a = new Thread(esteira1);
		Thread b = new Thread(esteira2);
		a.start();
		b.start();
		a.join();
		b.join();
		srt.qntsAtendidos();


	}
}
