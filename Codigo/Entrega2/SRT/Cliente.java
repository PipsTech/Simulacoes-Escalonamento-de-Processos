import java.text.DecimalFormat;

public class Cliente implements Comparable<Cliente>{
	private String nome;
	private int NroPacotes;
	private double tempoDePacotes;
	private int Prazo;
	private int tempo;
	private boolean DentroDoPrazo;
	private double ComeçarExecução;
	private boolean Executavel;
	private boolean atendido;


	public double getComeçarExecução() {
		return ComeçarExecução;
	}

	public void setComeçarExecução(double top) {
		this.ComeçarExecução = top;
	}
	public Cliente(String Infos) {
		String[] Info = Infos.split(";");
		this.nome = Info[0];
		this.NroPacotes = Integer.parseInt(Info[1]);
		this.Prazo = Integer.parseInt(Info[2]);
		this.tempo = Integer.parseInt(Info[3]);
	}
	
	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public String getNome() {
		return nome;
	}

	public int getNroPacotes() {
		return NroPacotes;
	}

	public int getPrazo() {
		return Prazo;
	}

	public boolean isDentroDoPrazo() {
		return DentroDoPrazo;
	}

	public void setDentroDoPrazo(boolean dentroDoPrazo) {
		DentroDoPrazo = dentroDoPrazo;
	}
	
	public double getTempoDePacotes() {
		return tempoDePacotes;
	}

	public void setTempoDePacotes(double d) {
		this.tempoDePacotes = d;
	}

	public boolean isExecutavel() {
		return Executavel;
	}

	public void setExecutavel(boolean executavel) {
		Executavel = executavel;
	}

	public int compareTo(Cliente c2)
	{
		if(this.getTempoDePacotes() > c2.getTempoDePacotes())
		{
			return 1;
		}
		else if(this.getTempoDePacotes() == c2.getTempoDePacotes())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		return "Cliente [nome=" + nome + ", NroPacotes=" + NroPacotes + ", tempoDePacotes=" + df.format(tempoDePacotes)
				+ ", Prazo=" + Prazo + ", tempo=" + tempo + ", DentroDoPrazo=" + DentroDoPrazo 
				 + "]";
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido(boolean atendido) {
		this.atendido = atendido;
	}

	
	

}
