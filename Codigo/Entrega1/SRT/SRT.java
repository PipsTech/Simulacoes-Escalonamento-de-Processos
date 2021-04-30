import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
public class SRT {
	Leitura Ler = new Leitura();
	Cliente[] Clientes = new Cliente[1000];
	double Tempo = 0;
	public SRT()
	{
		
	}
	public String[] lerArquivo(String NomeArquivo) throws IOException
	{
		Ler.abrirArquivo(NomeArquivo);
		String Linhas = Ler.ler();
		int NroLinhas = Integer.parseInt(Linhas);
		String[] Valor = new String[NroLinhas];

		for(int i = 0; i <= NroLinhas; i++)
		{
			String Valor1 = Ler.ler();
			if(Valor1 == null)
			{
				break;
			}
			Valor[i] = Valor1;
		}
		return Valor;
	}
	
	public void OrganizarInformações(String[] Informações) {
		for(int i = 0; i < Informações.length; i++)
		{
			String[] C = Informações[i].split(";");
			String Nome = C[0];
			String A = C[1];
			String B = C[2];
			int Pacotes = Integer.parseInt(A);
			int Tempo = Integer.parseInt(B);
			Cliente X = new Cliente(Nome, Pacotes, Tempo);
			Clientes[i] = X;
		}
	}
	
	public void calcularTempo() {
		for(int i = 0; i < Clientes.length; i++)
		{
			if(Clientes[i] == null)
			{
				break;
			}
			BigDecimal b = new BigDecimal(Tempo).setScale(1, RoundingMode.HALF_EVEN);
			Clientes[i].setComeçarExecução(b.doubleValue());
			double Segundos = ((Clientes[i].getNroPacotes() / 20) * 5.5);
			double Minutos = (Segundos / 60);
			BigDecimal bd = new BigDecimal(Minutos).setScale(1, RoundingMode.HALF_EVEN);
			Clientes[i].setTempo(bd.doubleValue());
			Tempo = Tempo + bd.doubleValue();
		}
	}
	
	public void menorTempo()
	{
		int i;
		for(int j=1; j < Clientes.length;j++) {
			if(Clientes[j]==null) {
				break;
			}
			Cliente temp = Clientes[j];
			for(i=j-1; (i>=0)&&(Clientes[i].getTempo()>temp.getTempo()); i--) {
				Clientes[i+1]=Clientes[i];
			}
			Clientes[i+1]=temp;
		}
		for(int h = 0; h < Clientes.length; h++)
		{
			if(Clientes[h]==null) {
				break;
			}
		}
	}

	public void Printar() {
		for(int i = 0; i < Clientes.length; i++)
		{
			if(Clientes[i] == null)
			{
				break;
			}
			System.out.println(Clientes[i].toString());
		}
	}
	
	public void DentroDoPrazo()
	{
		for(int i = 0; i < Clientes.length; i++)
		{
			if(Clientes[i] == null)
				break;
			if((Clientes[i].getPrazo() >= Clientes[i].getTempo() || Clientes[i].getPrazo() == 0) && Clientes[i].getComeçarExecução() < 540)
				Clientes[i].setDentroDoPrazo(true);
			else
				Clientes[i].setDentroDoPrazo(false);
		}
	}
	
	public double tempoMedio()
	{
		int i;
		double Media = 0;
		for(i = 0; i < Clientes.length; i++)
		{
			if(Clientes[i] == null)
				break;
			Media = Media + Clientes[i].getComeçarExecução();
		}
		double resultado = Media / i - 1;
		resultado = Math.round(resultado);
		return resultado;
	}
}
