import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;


public class Prioridade {

	private Leitura le;
    
	private Pedido[] pedidos = new Pedido[1000];
	private static int cont =0;
	
	
    public Prioridade(){
        le = new Leitura();
    }
    public void leArquivo(String arquivo) throws IOException{
        
        le.abrirArquivo(arquivo);
        String aux = le.ler();
      
        String[] temp= new String[319]; 
        for(int i=0;i < 319;i++){
            temp[i] = le.ler();
            cont++;
            this.pedidos[i] = new Pedido(temp[i]);
           
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
        int aux;
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
  
	
    public Pedido[] getPedidos() {
		return pedidos;
	}
	
   
    public void printPedidosAtendidos(){
    	int cont=0;
        for(int i=0;i<Prioridade.cont;i++) {
        	if(pedidos[i].isAtendimento()) {
        		System.out.println(pedidos[i]);
        		cont++;
        	}
        }
        System.out.println("Quantidade atendida: "+cont);
        this.media();
      
    }
 
 
	public static int getCont() {
		return cont;
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
	public void printAteMeioDia() {
		int cont=0;
		 System.out.println("Pedidos concluidos ate meio dia: ");
		 for(int i=0;i<Prioridade.cont;i++) {
	        	if(pedidos[i].isAtendimento() && pedidos[i].getTempo() <=480.0) {
	        		System.out.println(pedidos[i]);
	        		cont++;
	        	}
	        }
		 System.out.println("Quantidade ate meio dia: "+ cont);
	}
	
  
}
