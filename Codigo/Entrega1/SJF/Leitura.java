import java.io.*;

public class Leitura {
	private static BufferedReader entrada;

	public void abrirArquivo(String nomeArquivo) throws FileNotFoundException{	
			entrada = new BufferedReader(new FileReader(nomeArquivo));
	}
	
	public void fecharArquivo() throws IOException {
			entrada.close();	
	}
	
	public String ler() throws IOException {
		String textoEntrada;
		textoEntrada = entrada.readLine();
		return textoEntrada;
	}
}
