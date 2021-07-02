import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ThreadPiscina implements Runnable{
	
	Semaphore listLock;
	Semaphore contador;
	
	private int cont = 0;
	
	private SRT srt;
	
	public ThreadPiscina(SRT srt2) {
		this.listLock = new Semaphore(1);
		this.contador = new Semaphore(0);
		this.srt = srt2;
		srt.calcularTempo();

	}

	public void rodar() throws IOException {
		this.cont = this.srt.executavel(cont);
		System.out.println(Thread.currentThread().getName() + " " + this.srt.Printar(cont));
	}
	
	public int getCont() {
		return cont;
	}

	@Override
	public void run() {
		while(!(cont == -1))
		{
			try {
				contador.tryAcquire();
				this.rodar();
				contador.release();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
