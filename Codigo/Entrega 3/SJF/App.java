import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Semaphore;



public class App {

	  public static void main(String[]args) throws IOException, InterruptedException{
		 Sjf p = new Sjf();
		  p.leArquivo("pedidos.txt");
		  p.leArquivoProdutos("produtos.txt");
		  p.orgSJF();
		
		  
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
	class Esteira implements Runnable{
	
		private Sjf sjf;
		private Pedido[] pedidos;
		private int cont;
		private double tempoTotal =0;
		private static int indice =0;
		private Map<Integer,Integer> produtos;
		private Semaphore semaforo = new Semaphore(1);
	
		@Override
		public void run() {

				        for(int i=0;i <cont;i++){
				        	try {
								semaforo.acquire();
							} catch (InterruptedException e1) {
							
								e1.printStackTrace();
							}
				        	
				       
				        	if(pedidos[i].getChegada() > tempoTotal) {
				        		
				        		sjf.reorganiza(i);
				        	
				        	
				        	}
				        	
								indice = sjf.escalona(indice);
								semaforo.release();

				        }
				    
			
		}
		
	
	


	public Esteira(Sjf p) {
		this.sjf = p;
		pedidos = p.getPedidos();
		cont = Sjf.getCont();
		produtos =this.sjf.getProdutos();
		
		}
	}
