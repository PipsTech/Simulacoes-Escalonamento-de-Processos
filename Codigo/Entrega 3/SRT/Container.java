
public class Container {
	private int tipo;
	private static final int VOLUMETOTAL = 1000000;
	private double volume;
	
	public Container(int tipo) {
		this.setTipo(tipo);
	}
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public double getVolume() {
		return volume;
	}
	public boolean setVolume(double vol) {
		if(this.getVolume() + vol < this.VOLUMETOTAL)
		{
			this.volume = vol;
			return true;
		}
		return false;
		
	}
	public static int getVolumetotal() {
		return VOLUMETOTAL;
	}
	
}
