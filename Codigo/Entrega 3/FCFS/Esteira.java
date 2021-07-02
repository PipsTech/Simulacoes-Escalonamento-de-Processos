import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;

public class Esteira implements Runnable {

	private FCFS p;
	private Pedido[] pedidos;
	private int cont;
	private double tempoTotal = 0;
	private Container[] containers = new Container[4];
	private List<Container> containersReserva = new ArrayList<Container>();
	private Semaphore semaforo = new Semaphore(1);
	
	
	@Override
	public void run() {
		for (int i = 0; i < cont; i++) {
			if (tempoTotal > 1080.0) {
				break;
			}
			try {

				semaforo.acquire();
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			if(pedidos[i]!= null) {//ALTEREI
				if (pedidos[i].getChegada() > tempoTotal) {// Acabou

					reorganiza(i);// ele vai tentar pegar um outro pedido com tempo menor pra ve se da tempo

				}
				
			}
		
			
			
			escalona(pedidos[i]);
		}

	}

	public void escalona(Pedido p) {
		if(p!=null) { //ALTEREI
			
		
		double taux;
		double pacotes = 1;
		double volumeAtual = this.p.getProdutos()[p.getIdProduto()].getVolume();

		
		if (p.getNprodutos() * volumeAtual > 5000) {
			pacotes = p.getNprodutos() * volumeAtual / 5000;
		}

		taux = (double) (5.5 * pacotes) / 60 + tempoTotal
				+ this.p.trocarConteiner(this.p.getProdutos()[p.getIdProduto()]);
		tempoTotal += taux;
		if (tempoTotal > 540) {
			taux = taux / 2.0;
		}
		p.setTempo(taux);
		p.setAtendimento(true);
		System.out.println(Thread.currentThread().getName() + " " + p.toString());
		
		}
		
		semaforo.release();
		

	}

	public void reorganiza(int i) {
		Pedido aux;

		for (int j = i; j < cont; j++) {
			if (pedidos[j].getChegada() <= tempoTotal) {
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