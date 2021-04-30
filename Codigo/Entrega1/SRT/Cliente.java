public class Cliente {
	private String nome;
	private int NroPacotes;
	private int Prazo;
	private double tempo;
	private boolean DentroDoPrazo;
	private double ComeçarExecução;

	public double getComeçarExecução() {
		return ComeçarExecução;
	}

	public void setComeçarExecução(double começarExecução) {
		ComeçarExecução = começarExecução;
	}

	public Cliente(String NOME, int PACOTES, int PRAZO) {
		this.nome = NOME;
		this.NroPacotes = PACOTES;
		this.Prazo = PRAZO;
	}
	
	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
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

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", NroPacotes=" + NroPacotes + ", Prazo=" + Prazo + ", tempo=" + tempo
				+ ", DentroDoPrazo=" + DentroDoPrazo + ", ComeçarExecução=" + ComeçarExecução + "]";
	}
	

}
