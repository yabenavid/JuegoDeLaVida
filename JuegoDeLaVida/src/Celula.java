import java.util.Random;

public class Celula {

	private boolean estadoDeVida;
	private boolean inmunidad;
	private boolean salud;
	private int tiempoDeVida;

	public Celula() {
		super();
		this.estadoDeVida = false;
		this.inmunidad = false;
		this.salud = true;
		this.tiempoDeVida = 0;
	}

	public Celula(boolean estadoDeVida, boolean inmunidad, boolean salud, int tiempoDeVida) {
		super();
		this.estadoDeVida = estadoDeVida;
		this.inmunidad = inmunidad;
		this.salud = salud;
		this.tiempoDeVida = tiempoDeVida;
	}
	
	public double evaluarCelula() {
		Random rn = new Random();
		return rn.nextDouble();
	}

	public boolean isEstadoDeVida() {
		return estadoDeVida;
	}

	public void setEstadoDeVida(boolean estadoDeVida) {
		this.estadoDeVida = estadoDeVida;
	}

	public boolean isInmunidad() {
		return inmunidad;
	}

	public void setInmunidad(boolean inmunidad) {
		this.inmunidad = inmunidad;
	}

	public boolean isSalud() {
		return salud;
	}

	public void setSalud(boolean salud) {
			this.salud = salud;
		
		
	}

	public int getTiempoDeVida() {
		return tiempoDeVida;
	}

	public void setTiempoDeVida(int tiempoDeVida) {
		this.tiempoDeVida = tiempoDeVida;
	}
}
