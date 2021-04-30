import java.io.*;

public class app {
	public static void main(String[] args) throws IOException {
		SRT srt = new SRT();
		String[] Valor = srt.lerArquivo("SO_20_DadosEmpacotadeira1.txt");
		srt.OrganizarInformações(Valor);
		srt.calcularTempo();
		srt.menorTempo();
		srt.DentroDoPrazo();
		srt.Printar();
		System.out.println(srt.tempoMedio());
	}
}
