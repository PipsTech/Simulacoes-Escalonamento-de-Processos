
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Sjf{
    private Leitura le;
    private Pedido[] pedidos = new Pedido[319]; 
    private static int cont=0;
    private static int indice=0;
    private double tempoTotal=0;
    private Semaphore semaforo = new Semaphore(1);
    private List<Container> containers = new ArrayList<Container>();
	private List<Container> conUsados = new ArrayList<Container>();
	private Map<Integer,Integer> produtos = new HashMap<Integer,Integer>();
    public Sjf(){
        le = new Leitura();
    }
 public void leArquivo(String arquivo) throws IOException{
        
        le.abrirArquivo(arquivo);
        String aux = le.ler();
        int tam  = Integer.parseInt(aux);
        String[] temp= new String[tam]; 
        for(int i=0;i < tam;i++){
            temp[i] = le.ler();
            cont++;
            this.pedidos[i] = new Pedido(temp[i]);
           
        }
        le.fecharArquivo();
    }
	public void leArquivoProdutos(String arquivo) throws IOException {
		le.abrirArquivo(arquivo);
		String aux = le.ler();
		int tam  = Integer.parseInt(aux);
		String[] temp = new String[tam]; 
		for (int i = 0; i < tam; i++) {
			temp[i] = le.ler();
			Produto ptemp = new Produto(temp[i]);
			this.produtos.put(ptemp.getId(),ptemp.getVolume());
			this.containers.add(new Container(ptemp.getId()));
			if(i <= 3) {
				this.conUsados.add(new Container(ptemp.getId()));
			}
		}
		le.fecharArquivo();
	}
    public void orgSJF(){
        
        int i;
		for(int j=1; j < pedidos.length;j++) {
			if(pedidos[j]==null) {
				break;
			}
			Pedido temp = pedidos[j];
           
			for( i=j-1;(i>=0)&&(pedidos[i].getNprodutos()>temp.getNprodutos());i--) {
                
				pedidos[i+1]=pedidos[i];
            
			}
			pedidos[i+1]=temp;
        
		}
    }
  
    public void quantosAtendidos(){
    	int cont=0;
        for(int i=0;i<Sjf.cont;i++) {
        	if(pedidos[i].isAtendimento()) {
        		cont++;
        	}
        }
        System.out.println("Quantidade atendida: "+cont);
        this.media();
      
    }
 
	public void media(){
        double soma = 0;
        int i;
        for( i=0;i<cont;i++) {
        	if(pedidos[i].isAtendimento()) {
        		soma+= pedidos[i].getTempo();
        	}
        }
        double media = (double) soma / i;
        System.out.println("Tempo médio de retorno: "+ media);
    }
	public void quantosAteMeioDia() {
		int cont=0;
		 for(int i=0;i<Sjf.cont;i++) {
	        	if(pedidos[i].isAtendimento() && pedidos[i].getTempo() <=270.0) {
	        		cont++;
	        	}
	        }
		 System.out.println("Quantidade atendida ate meio dia: "+ cont);
	}
	  public Pedido[] getPedidos() {
			return pedidos;
		}
	  public static int getCont() {
			return cont;
		}
	  public Map<Integer,Integer> getProdutos() {
			return produtos;
		}
		
		public List<Container> getContainers() {
			return containers;
		}
		
		public List<Container> getConUsados() {
			return conUsados;
		}
		public synchronized  int escalona(int i)  {
			if(i < cont -1) {
			Pedido p = pedidos[i];
			if(tempoTotal < 1080.0) {
	    	double taux =0;
	    	double pacotes =1 ;
			if(p.getNprodutos()>20){
				pacotes = p.getNprodutos()/20;
			}
			int vol = produtos.get(p.getIdProduto()) * p.getNprodutos();
			if(vol > 5000) {
				pacotes += vol/5000;
			}
			
			long j = this.getConUsados().stream().filter((f) -> f.getTipo() == p.getIdProduto()).count();
			if(j == 0) {
				for(Container c : this.getContainers()) {
					if(c.getTipo() == p.getIdProduto()) {
						trocaContainer(c);
						taux += 0.5;
						break;
					}
				}
				
			} 
			for(Container con : this.getConUsados()) {
				if(con.getTipo() == p.getIdProduto()) {
					con.setVolume(con.getVolume() + vol);
				}
			}
			taux += (double)(5.5*pacotes)/60 + tempoTotal;  
			tempoTotal =  taux;
			if(tempoTotal > 540) {
				taux = taux/2.0;
			}
			p.setTempo(taux);
			p.setAtendimento(true);
			System.out.println(Thread.currentThread().getName() + " " + p.toString());
			}
			}
			
		return indice+=1;
			
	    	
			
	    	
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
		
		public void trocaContainer(Container c) {
			this.getConUsados().remove(0);
			this.getConUsados().add(c);
		}
}

