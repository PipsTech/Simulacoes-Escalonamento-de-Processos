
import java.io.IOException;

public class Sjf{
    private Leitura le;
    private Pedido[] pedidos = new Pedido[319]; 
    private static int cont=0;
    private double tempoTotal=0;
    public Sjf(){
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
 
    public void printPedidosAtendidos(){
    	int cont=0;
        for(int i=0;i<Sjf.cont;i++) {
        	if(pedidos[i].isAtendimento()) {
        		System.out.println(pedidos[i]);
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
	public void printAteMeioDia() {
		int cont=0;
		 System.out.println("Pedidos concluidos ate meio dia: ");
		 for(int i=0;i<Sjf.cont;i++) {
	        	if(pedidos[i].isAtendimento() && pedidos[i].getTempo() <=240.0) {
	        		System.out.println(pedidos[i]);
	        		cont++;
	        	}
	        }
		 System.out.println("Quantidade ate meio dia: "+ cont);
	}
	  public Pedido[] getPedidos() {
			return pedidos;
		}
	  public static int getCont() {
			return cont;
		}
}

