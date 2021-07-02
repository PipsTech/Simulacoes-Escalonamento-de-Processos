
public class Container {
	
	private int tipo;

	private int volume;
	private static final int VOLUMEMAX = 1000000;

	
	public Container( int tipo) {
		this.setTipo(tipo);
		this.setVolume(0);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		if(volume <= VOLUMEMAX)
		this.volume = volume;
	}

}
