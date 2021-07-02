import java.io.IOException;
import java.util.concurrent.Semaphore;



public class Aplicacao {

	  public static void main(String[]args) throws IOException, InterruptedException{
		 Prioridade p = new Prioridade();
		  p.leArquivo("SO_20_DadosEmpacotadeira_2.txt");
		  p.orgPrioridade();
		
		  
		  Thread t1 = new Thread(new Esteira(p));
		  Thread t2 = new Thread(new Esteira(p));
		  t1.start();
		  t2.start();
		  t1.join();
		  t2.join();
		
		 p.printAteMeioDia();
		 System.out.println();
		
		 p.printPedidosAtendidos();
	      
	      
	     
	    }

	
}
	class Esteira implements Runnable{
	
		private Prioridade p;
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
		System.out.println(Thread.currentThread().getName());
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
	


	public Esteira(Prioridade p) {
		this.p = p;
		pedidos = p.getPedidos();
		cont = Prioridade.getCont();
		
		
		}
	}
