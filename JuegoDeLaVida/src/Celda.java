
public class Celda {

	private Coordenada coordenada;
	private Celula celula;
	
	public Celda() {
		super();
		this.coordenada = new Coordenada();
		this.celula = new Celula();
	}
	
	public Celda(Coordenada coordenada, Celula celula) {
		super();
		this.coordenada = coordenada;
		this.celula = celula;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}
	
	
	
}
