import java.io.IOException;
import java.util.concurrent.Semaphore;



public class Aplicacao {

	  public static void main(String[]args) throws IOException, InterruptedException{
		 Prioridade p = new Prioridade();
		  p.leArquivo("pedidos2.txt");
		  p.leArquivoProdutos("produtos.txt");
		  p.orgPrioridade();
		
		  
		  Thread t1 = new Thread(new Esteira(p));
		  Thread t2 = new Thread(new Esteira(p));
		  t1.start();
		  t2.start();
		  t1.join();
		  t2.join();
		  p.quantosAtendidos();
		  p.quantosAteMeioDia();
		
	    
	      
	     
	    }

	
}
	class Esteira implements Runnable {
	
		private Prioridade p;
		private Pedido[] pedidos;
		private int cont;
		private static int indice = 0;
		private double tempoTotal =0;
		private Semaphore semaforo = new Semaphore(1);
		
	
	
		@Override
		public void run() {

				        for(int i=0;i <cont;i++) {
				        	try {
								semaforo.acquire();
							} catch (InterruptedException e1) {
							
								e1.printStackTrace();
							}
				        	
				       
				        	if(pedidos[i].getChegada() > tempoTotal) {
				        		
				        		p.reorganiza(i);
				        	
				        	
				        	}
				        		
								indice = p.escalona(indice);
								semaforo.release();

				        }
				    
			
		}
	


	public Esteira(Prioridade p) {
		this.p = p;
		pedidos = p.getPedidos();
		cont = Prioridade.getCont();
	
		
		}
	}
