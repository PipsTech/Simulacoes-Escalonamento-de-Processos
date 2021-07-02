import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SRT {
	private final int MAX = 4;
	private double TempoTotal = 0;
	private int contContainers = 0;
	private int cont;
	private int atendidos = 0;
	private Map<Integer,Integer> Produtos;
	private List<Pedido> Pedidos;
	private List<Container> Containers;
	private List<Container> containersEmUso;

	public SRT(String arq1, String arq2) throws IOException {
		this.Produtos = new HashMap<Integer, Integer>();
		this.Pedidos = new ArrayList<Pedido>();
		this.Containers = new ArrayList<Container>();
		this.containersEmUso = new ArrayList<Container>();
		this.LerPedidos(arq1);
		this.LerProdutos(arq2);
	}

	public synchronized int executavel(int contador) {
		if(contador < this.Pedidos.size() - 1)
		{
			if(this.TempoTotal >= this.Pedidos.get(contador).getChegada())
			{
				this.Pedidos.get(contador).setComecarExecucao(this.TempoTotal);
				this.Pedidos.get(contador).setAtendido(true);
				Container C = null;
				for(Container c : Containers) {
					if(this.Pedidos.get(contador).getCodigo() == c.getTipo())
					{
						C = c;
						break;
					}
				}
				if(contContainers > this.MAX && !(this.containersEmUso.contains(C)))
				{
					this.trocarContainer(C);
					this.TempoTotal += 30;
				}
				else
				{
					this.containersEmUso.add(C);
					this.contContainers++;
				}
				double vol = this.Produtos.get(this.Pedidos.get(contador).getCodigo()) * this.Pedidos.get(contador).getQtProduto();
				this.Pedidos.get(contador).setDentroDoContainer(C.setVolume(vol));
				this.Pedidos.get(contador).setVolume(vol);
			}
			else
			{
				Pedido P = this.Pedidos.get(contador);
				this.Pedidos.remove(contador);
				this.Pedidos.add(P);
			}
			if(this.Pedidos.get(contador).getComecarExecucao() > 1080)
			{
				this.Pedidos.get(contador).setComecarExecucao(-1);
				this.Pedidos.get(contador).setAtendido(false);
				this.Pedidos.get(contador).setDentroDoPrazo(false);
			}
			else
			{
				this.TempoTotal += this.Pedidos.get(contador).getTempoDePacote();
				this.atendidos++;
			}
			return cont++;
		}
		else
		{
			cont = -1;
			return cont;
		}
	}
	public List<Pedido> getPedidos() {
		return Pedidos;
	}

	private void trocarContainer(Container C) {
		this.containersEmUso.remove(0);
		this.containersEmUso.add(0, C);
	}
	public void organizaTempoPacote() {
		this.Pedidos.stream().filter((m) -> m.getPrazo() == 0).forEach((m) -> m.setDentroDoPrazo(true));
		this.Pedidos.stream().filter((m) -> m.isDentroDoPrazo() == false).filter((m) -> m.getTempoDePacote() < m.getPrazo()).forEach((m) -> m.setDentroDoPrazo(true));
		Collections.sort(Pedidos);
	}
	private void LerProdutos(String nomeArq) throws IOException {
		Leitura Ler = new Leitura();
		Ler.abrirArquivo(nomeArq);
		String[] Valores;
		String Linha = Ler.ler();
		int Linhas = Integer.parseInt(Linha);
		for(int i = 0; i < Linhas; i++){
			Linha = Ler.ler();
			Valores = Linha.split(";");
			this.Produtos.put(Integer.parseInt(Valores[0]),Integer.parseInt(Valores[1]));
			this.Containers.add(new Container(Integer.parseInt(Valores[0])));
		}
		Ler.fecharArquivo();
	}

	private void LerPedidos(String nomeArq) throws IOException {
		Leitura Ler = new Leitura();
		Ler.abrirArquivo(nomeArq);
		String Linha = Ler.ler();
		Linha = Linha.replace("﻿", "");
		int Linhas = Integer.parseInt(Linha);
		for(int i = 0; i <= Linhas - 1; i++){
			Linha = Ler.ler();
			this.Pedidos.add(new Pedido(Linha));
		}
		Ler.fecharArquivo();
	}
	public void printPedidos() {
		for(Pedido p : Pedidos) {
			System.out.println(p.toString());
		}
	}
	public void printContainers()
	{
		for(Container c : Containers)
		{
			System.out.println(c.toString());
		}
	}
	public String Printar(int i) {
		String f = "";
		if(i < this.Pedidos.size() && i >= 0)
		{
			f = this.Pedidos.get(i).toString();
		}
		return f;	
	}

	public double getTempoTotal() {
		return TempoTotal;
	}

	public int getAtendidos() {
		return atendidos;
	}
	
}
