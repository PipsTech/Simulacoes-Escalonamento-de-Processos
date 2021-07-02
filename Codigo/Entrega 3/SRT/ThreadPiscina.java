import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ThreadPiscina implements Runnable{
	private SRT srt;
	private int contador;
	Semaphore listLock;

	public ThreadPiscina(SRT srt)
	{
		this.listLock = new Semaphore(1);
		this.srt = srt;
	}

	public void rodar() throws IOException {
		this.contador = this.srt.executavel(contador);

	}

	@Override
	public void run() {
		while(!(contador == -1)) {
			try {
				listLock.tryAcquire();
				this.rodar();
				listLock.release();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
