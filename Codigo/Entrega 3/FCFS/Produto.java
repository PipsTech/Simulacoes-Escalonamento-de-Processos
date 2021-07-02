
public class Produto {
	
	private int id;
	private int volume;

	public Produto(String str) {

		String campos[] = str.split(";");
		this.id = Integer.parseInt(campos[0]);
		this.volume = Integer.parseInt(campos[1]);

	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getId() {
		return this.id;
	}

}
