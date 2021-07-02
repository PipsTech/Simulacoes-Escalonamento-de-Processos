import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.concurrent.Semaphore;


public class Prioridade {

	private Leitura le;
    
	private Pedido[] pedidos ;
	private static int cont =0;
	private double tempoTotal =0;
	private static int indice =0;
	private List<Container> containers = new ArrayList<Container>();
	private List<Container> conUsados = new ArrayList<Container>();
	private Map<Integer,Integer> produtos = new HashMap<Integer,Integer>();
	
    public Prioridade(){
        le = new Leitura();
        pedidos = new Pedido[1000];
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
   public void ordenaPrazo(){
        int i;
		for(int j=1; j < cont;j++) {
			Pedido temp = pedidos[j];
           if(temp == null) {
        	   break;
           }
			for( i=j-1;(i>=0)&&(pedidos[i].getPrazo()>temp.getPrazo() || temp.getPrazo()==pedidos[j].getPrazo()&&temp.getNprodutos()>pedidos[j].getNprodutos());i--) {
                
				pedidos[i+1]=pedidos[i];
            
			}
			pedidos[i+1]=temp;
        
		}
    }
    public void defPrioridade(){
        this.ordenaPrazo();
        int aux=0;
        int pri;
        for(int i=0;i<cont;i++){
        	if(pedidos[i] == null) {
        		break;
        	}
            if(pedidos[i].getPrazo()==0){
                pri = pedidos.length + i;
                aux++;
            }
            else{
                pri = i + 1 - aux;
            }
            pedidos[i].setPrioridade(pri);
            
        }
    }
    public void orgPrioridade(){
        this.defPrioridade();
        int i;
		for(int j=1; j < cont;j++) {
			
			Pedido temp = pedidos[j];
           if(temp == null) {
        	   break;
           }
			for( i=j-1;(i>=0)&&(pedidos[i].getPrioridade()>temp.getPrioridade());i--) {
                
				pedidos[i+1]=pedidos[i];
            
			}
			pedidos[i+1]=temp;
        
		}
    }
  
	
    public void quantosAtendidos(){
    	int cont=0;
        for(int i=0;i<Prioridade.cont;i++) {
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
		 for(int i=0;i<Prioridade.cont;i++) {
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
	public synchronized int escalona(int i)  {
		
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
	
		if(!isUsado(p)) {
			for(Container c : this.getContainers()) {
				if(c.getTipo() == p.getIdProduto()) {
					trocaContainer(c);
					taux += 0.5;
					break;
				}
			}
			
		}
			
			preencheContainer(p,vol);
			
		taux += (double)(5.5*pacotes)/60 + tempoTotal;  
		tempoTotal =  taux;
		
		if(tempoTotal > 540) {
			taux = taux/2.0;
		}
		p.setTempo(taux);
		p.setAtendimento(true);
		System.out.println(Thread.currentThread().getName() + " " + p.toString());
		
		
		}
		
		return indice+=1;
		
    	
    }
	private boolean isUsado(Pedido p) {
		boolean uso = false;
		for(Container c : this.getConUsados()) {
			if(c.getTipo() == p.getIdProduto()) {
				uso = true;
				break;
			}
		}
		
		return uso;
		
	}
	 
	private void preencheContainer(Pedido p,int vol) {
		
		for(Container con : this.getConUsados()) {
			if(con.getTipo() == p.getIdProduto() ) {
				con.setVolume(con.getVolume() + vol);
				break;
			}
		}
	
		
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
