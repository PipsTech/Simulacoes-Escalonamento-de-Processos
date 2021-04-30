import java.io.IOException;


public class FCFS {
	private Leitura le;
	private Pedido[] pedidos = new Pedido[169];

	public FCFS() {
		le = new Leitura();
	}

	public void leArquivo(String arquivo) throws IOException {
		le.abrirArquivo(arquivo);
		String aux = le.ler();
		int linhas = Integer.parseInt(aux);
		String[] temp = new String[linhas];
		for (int i = 0; i < linhas; i++) {
			temp[i] = le.ler();
			this.pedidos[i] = new Pedido(temp[i]);
		}
		le.fecharArquivo();
	}

	
	public void escalona(){
        double tempoTotal=0;
        double taux;
        for(int i=0;i < pedidos.length;i++){
            double pacotes =1 ;
            if(pedidos[i].getNprodutos()>20){
                pacotes = pedidos[i].getNprodutos()/20;
               
            }
            if(tempoTotal > 540){
                break;
            }
            taux = (double)(5.5*pacotes)/60 + tempoTotal;  
            tempoTotal =  taux;
            pedidos[i].setTempo(taux);
            pedidos[i].setAtendimento(true);
        }

    }

	public void printPedidos() {
		this.escalona();
		for (int i = 0; i < pedidos.length - 1; i++) {
			System.out.println(pedidos[i].toString());
		}
	}
	public void media(){
        double soma=0;
        int i;
        for(i=0;i<pedidos.length;i++){
            soma = soma + pedidos[i].getTempo();
        }
        double media = soma/i;
        System.out.println("Tempo médio de retorno: "+ media);
    }


}
