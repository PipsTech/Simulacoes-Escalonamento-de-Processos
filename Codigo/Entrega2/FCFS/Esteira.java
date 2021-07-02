import java.io.IOException;
import java.util.concurrent.Semaphore;


public class Esteira implements Runnable{

	private FCFS p;
	private Pedido[] pedidos;
	private int cont;
	private double tempoTotal =0;
	private Semaphore semaforo = new Semaphore(1);

	@Override
	public void run() {

		for(int i=0;i <cont;i++){
			if(tempoTotal > 1080.0){
				break;
			}
			try {
				semaforo.acquire();
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}


			if(pedidos[i].getChegada() > tempoTotal) {

				reorganiza(i);


			}

			escalona(pedidos[i]);


		}


	}
	public   void escalona(Pedido p)  {
		double taux;
		double pacotes =1 ;
		if(p.getNprodutos()>20){
			pacotes = p.getNprodutos()/20;
		}

		taux = (double)(5.5*pacotes)/60 + tempoTotal;  
		tempoTotal =  taux;
		if(tempoTotal > 540) {
			taux = taux/2.0;
		}
		p.setTempo(taux);
		p.setAtendimento(true);
		System.out.println(Thread.currentThread().getName() + " " + p.toString());
		semaforo.release();

	}
	public void reorganiza(int i) {
		Pedido aux ;

		for(int j=i; j<cont;j++) {
			if(pedidos[j].getChegada() <= tempoTotal) {
				aux = pedidos[i];
				pedidos[i] = pedidos[j];
				pedidos[j] = aux;
				break;
			}
		}



	}

	public Esteira(FCFS p) {
		this.p = p;
		pedidos = p.getPedidos();
		cont = FCFS.getCont();


	}
}