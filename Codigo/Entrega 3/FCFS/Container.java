import java.util.ArrayList;
import java.util.List;

public class Container {
	private int vetor[] = new int[4];

	private int vetorReserva[];

	private int tipo;

	private int qtdProdutos;

	
	public Container( int tipo) {
		this.tipo = tipo;
	}

	public int[] getVetor() {
		return vetor;
	}

	public void setVetor(int[] vetor) {
		this.vetor = vetor;
	}

	public int[] getVetorReserva() {
		return vetorReserva;
	}

	public void setVetorReserva(int[] vetorReserva) {
		this.vetorReserva = vetorReserva;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

}
