import java.text.DecimalFormat;

public class Pedido implements Comparable<Pedido>{
	private String Nome;
	private int qtProduto;
	private int Prazo;
	private int Chegada;
	private int codigo;
	private double comecarExecucao;
	private double tempoDePacote;
	private boolean Atendido;
	private boolean dentroDoPrazo;
	private boolean DentroDoContainer;
	private double volume;

	public Pedido(String str)
	{
		String[] valores = str.split(";");
		this.setNome(valores[0]);
		this.setQtProduto(Integer.parseInt(valores[1]));
		this.setPrazo(Integer.parseInt(valores[2]));
		this.setChegada(Integer.parseInt(valores[3]));
		this.setCodigo(Integer.parseInt(valores[4]));
		this.calcularTempo();
		this.Atendido = false;
	}

	public void calcularTempo() {
		this.setTempoDePacote(((this.getQtProduto() / 20) * 5.5) / 60);
	}

	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		this.Nome = nome;
	}
	public int getQtProduto() {
		return qtProduto;
	}
	public void setQtProduto(int qtProduto) {
		this.qtProduto = qtProduto;
	}
	public int getPrazo() {
		return Prazo;
	}
	public void setPrazo(int prazo) {
		this.Prazo = prazo;
	}
	public int getChegada() {
		return Chegada;
	}
	public void setChegada(int chegada) {
		this.Chegada = chegada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getTempoDePacote() {
		return tempoDePacote;
	}
	public void setTempoDePacote(double tempoDePacote) {
		this.tempoDePacote = tempoDePacote;
	}
	public boolean isAtendido() {
		return Atendido;
	}
	public void setAtendido(boolean atendido) {
		this.Atendido = atendido;
	}
	public double getComecarExecucao() {
		return comecarExecucao;
	}
	public void setComecarExecucao(double comecarExecucao) {
		this.comecarExecucao = comecarExecucao;
	}
	public boolean isDentroDoPrazo() {
		return dentroDoPrazo;
	}
	public void setDentroDoPrazo(boolean dentroDoPrazo) {
		this.dentroDoPrazo = dentroDoPrazo;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public boolean isDentroDoContainer() {
		return DentroDoContainer;
	}
	public void setDentroDoContainer(boolean dentroDoContainer) {
		DentroDoContainer = dentroDoContainer;
	}
	
	public String foiAtendido() {
		if(this.isAtendido())
		{
			return "Foi Atendido";
		}
		else
		{
			return "Não foi Atendido";
		}
	}
	public String foiDentroDoPrazo() {
		if(this.isDentroDoContainer())
		{
			return "Sim";
		}
		else
		{
			return "Não";
		}
	}
	public String foiDentroDoContainer() {
		if(this.isDentroDoContainer())
		{
			return "Sim";
		}
		else
		{
			return "Não";
		}
	}
	public String AvaliarComeçarExecucao() {
		DecimalFormat df = new DecimalFormat("##.###");
		if(this.getComecarExecucao() == -1)
		{
			return "Não foi Processado";
		}
		else
		{
			return "" + df.format(this.getComecarExecucao());
		}
	}

	@Override
	public int compareTo(Pedido o) {
		if (this.getTempoDePacote() < o.getTempoDePacote()) { 
			return -1; 
		} if (this.getTempoDePacote() > o.getTempoDePacote()) { 
			return 1; 
		} 
		return 0;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("##.###");
		return "Pedido [Nome=" + Nome + ", Quantidade de Produtos Pedidos: " + qtProduto + ", Prazo: " + Prazo + ", Momento da Chegada: " + Chegada
				+ ", Codigo do Volume: " + codigo + ", Momento em que se começou a processar este pedido: " + this.AvaliarComeçarExecucao() + ", Tempo de Produção do Pedido: " + df.format(this.getTempoDePacote())
				+ ", Status do Atendimento: " + this.foiAtendido() + ", Entrega esta dentro do Prazo? " + this.foiDentroDoPrazo() + ", Acessou o Container: "
				+ this.foiDentroDoContainer() + ", Volume ocupado no Container: " + volume + "]";
	}

}
