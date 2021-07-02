import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SRT {
	Leitura Ler = new Leitura();
	ArrayList<Cliente> Clientes;
	private int cont = 0;
	private int NroLinhas;
	private double Tempo = 0;
	public SRT()
	{
		this.Clientes = new ArrayList<Cliente>();
	}
	public void lerArquivo(String NomeArquivo) throws IOException
	{
		Ler.abrirArquivo(NomeArquivo);
		String Linhas = Ler.ler();
		this.NroLinhas = Integer.parseInt(Linhas);
		for(int i = 0; i <= this.NroLinhas; i++)
		{
			String Valor1 = Ler.ler();
			if(Valor1 == null)
			{
				break;
			}
			Cliente C = new Cliente(Valor1);
			Clientes.add(C);
		}
	}
	public void calcularTempo() {
		this.Clientes.stream().filter((m) -> m.getPrazo() == 0).forEach((m) -> m.setDentroDoPrazo(true));
		this.Clientes.stream().forEach((n) -> n.setTempoDePacotes(((n.getNroPacotes() / 20) * 5.5) / 60));
		this.Ordenar();
	}
	public void Ordenar() {
		this.Clientes.stream().sorted();
		this.DentroDoPrazo();
	}
	public void DentroDoPrazo()
	{
		this.Clientes.stream().filter((m) -> m.isDentroDoPrazo() == false).filter((m) -> m.getTempoDePacotes() < m.getPrazo()).forEach((m) -> m.setDentroDoPrazo(true));
	}
	public synchronized int  executavel(int i) {
		if(cont <= this.Clientes.size() && i <= this.NroLinhas)
		{
			if(this.Tempo >= this.Clientes.get(i).getTempo())
			{
				this.Clientes.get(i).setComeçarExecução(this.Tempo);
				this.Tempo = this.Clientes.get(i).getTempoDePacotes();
				this.Clientes.get(i).setAtendido(true);
			}
			else
			{
				Collections.swap(this.Clientes, i, this.Clientes.size() - 1);
			}
			if(this.Clientes.get(i).getComeçarExecução() > 540)
			{
				this.Clientes.get(i).setComeçarExecução(-1);
			}
			return cont++;
		}
		else
		{
			cont = -1;
			return cont;
		}
	}
	public double tempoMedio()
	{
		return this.Clientes.stream().filter((m) -> m.getComeçarExecução() != -1).mapToDouble((m) -> m.getComeçarExecução()).average().getAsDouble();
	}
	public String Printar(int i) {
		String f = "";
		if(i < this.NroLinhas && i >= 0)
		{
			 f = this.Clientes.get(i).toString();
		}
		return f;
	}
	public Cliente getPosição(int i)
	{
		return this.Clientes.get(i);
	}
	public int getCont() {
		return cont;
	}
	public double getTempo() {
		return Tempo;
	}
	public void qntsAtendidos()
	{
		int a = 0;
		for(int i = 0; i <= this.Clientes.size() -1; i++)
		{
			if(this.Clientes.get(i).isAtendido() == true)
			{
				a++;
			}
		}
		System.out.println(a);
	}
}
